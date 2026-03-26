package org.mailosz.crmrest.sales;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mailosz.crmrest.crmclient.ClientRepository;
import org.mailosz.crmrest.crmclient.CrmClientEntity;
import org.mailosz.crmrest.crmuser.CrmUserEntity;
import org.mailosz.crmrest.crmuser.UserRepository;
import org.mailosz.crmrest.crmuser.roles.RoleEntity;
import org.mailosz.crmrest.exception.types.*;
import org.mailosz.crmrest.product.Category;
import org.mailosz.crmrest.product.ProductCacheRepository;
import org.mailosz.crmrest.product.ProductState;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Sale service unit tests")
class SaleServiceTest {

    @Mock
    private SaleRepository saleRepository;
    @Mock
    private SaleItemRepository saleItemRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ProductCacheRepository cacheRepository;
    @Mock
    private SaleStageRepository stageRepository;
    @Captor
    private ArgumentCaptor<SaleEntity> saleEntityCaptor;

    @InjectMocks
    private SaleService saleService;

    @Nested
    @DisplayName("Sale creation unit tests")
    class createSaleTests{
        SaleCreateReq createReq;
        CrmClientEntity clientEntity;
        CrmUserEntity userEntity;
        SaleStage stageEntity;
        SaleEntity saleEntityRes;
        ProductState stateEntity1;
        ProductState stateEntity2;
        List<SaleItem> saleItemsRes;
        SaleResponse expectedSaleResponse;

        UUID prodStateId = UUID.randomUUID();
        UUID prodStateId2 = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        UUID stageId = UUID.randomUUID();
        UUID itemId = UUID.randomUUID();
        UUID itemId2 = UUID.randomUUID();
        UUID saleId = UUID.randomUUID();

        @BeforeEach
        void setUp() {
            List<SaleItemReq> saleItems = List.of(
                    new SaleItemReq(prodStateId.toString(), "test1", BigDecimal.ONE, BigDecimal.TEN),
                    new SaleItemReq(prodStateId2.toString(), "test2", BigDecimal.ONE, BigDecimal.TEN)
            );

            createReq = new SaleCreateReq(
                    clientId.toString(),
                    saleItems, "dummy data");

            clientEntity = new CrmClientEntity(
                    clientId, "test",
                    "testnip", "test", "testphone",
                    "mail@test.com", "dummy person");

            RoleEntity role = new RoleEntity();
            userEntity = new CrmUserEntity(
                    userId, "mail@test.com",
                    "testPass", role, "test-name", "test-surname");

            stageEntity = new SaleStage(stageId, Stage.CREATED);

            stateEntity1 = new ProductState(
                    prodStateId, "stateName1", "externalTestId1",
                    BigDecimal.valueOf(50.0), Category.OTHER, OffsetDateTime.now(ZoneOffset.UTC));

            stateEntity2 = new ProductState(
                    prodStateId2, "stateName2", "externalTestId2",
                    BigDecimal.valueOf(50.0), Category.OTHER, OffsetDateTime.now(ZoneOffset.UTC));

            saleEntityRes = new SaleEntity(saleId,
                    clientEntity, userEntity,
                    stageEntity, new ArrayList<>(),
                    OffsetDateTime.now(),
                    OffsetDateTime.now(),
                    null, BigDecimal.ZERO, "dummy data");

            saleItemsRes = new ArrayList<>(List.of(
                    new SaleItem(itemId, saleEntityRes, stateEntity1, "test1", BigDecimal.TEN, BigDecimal.ONE),
                    new SaleItem(itemId2, saleEntityRes, stateEntity2, "test2", BigDecimal.TEN, BigDecimal.ONE)
            ));

            BigDecimal total = saleItemsRes.stream().map(SaleItem::getSumPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
            saleEntityRes.setSumPrice(total);

            List<SaleItemResponse> itemsResp = List.of(
                    new SaleItemResponse(itemId.toString(), "test1", BigDecimal.ONE, BigDecimal.TEN, BigDecimal.valueOf(10.0)),
                    new SaleItemResponse(itemId2.toString(), "test2", BigDecimal.ONE, BigDecimal.TEN, BigDecimal.valueOf(10.0))
            );

            expectedSaleResponse = new SaleResponse(
                    saleId.toString(), "dummy data",
                    stageEntity.getStage().toString(),
                    itemsResp,saleEntityRes.getCreatedAt(),
                    saleEntityRes.getUpdatedAt(),
                    saleEntityRes.getCheckedAt(),
                    total, clientEntity.getName(),
                    clientEntity.getNipNumber());
        }
        @Test
        void shouldCreateSaleSuccessfully(){
//            Given
            when(clientRepository.findCrmClientEntityById(clientId))
                    .thenReturn(Optional.of(clientEntity));
            when(userRepository.findCrmUserEntityByMail(userEntity.getName()))
                    .thenReturn(Optional.of(userEntity));
            when(stageRepository.findSaleStageByStage(Stage.CREATED))
                    .thenReturn(Optional.of(stageEntity));

            when(cacheRepository.findProductStateById(prodStateId))
                    .thenReturn(Optional.of(stateEntity1));
            when(cacheRepository.findProductStateById(prodStateId2))
                    .thenReturn(Optional.of(stateEntity2));

            when(saleRepository.save(any(SaleEntity.class)))
                    .thenReturn(saleEntityRes);
            when(saleItemRepository.saveAll(anyList()))
                    .thenReturn(saleItemsRes);

//            When
            final SaleCreationResp testResult = saleService.createSale(createReq, userEntity.getName());
//            Then
            assertNotNull(testResult);
            assertEquals(expectedSaleResponse.getSaleId(), testResult.getSaleId());
            assertEquals(expectedSaleResponse.getSumPrice(), testResult.getSumPrice());

            verify(saleRepository).save(saleEntityCaptor.capture());
            SaleEntity savedSale = saleEntityCaptor.getValue();
            assertEquals(new BigDecimal("20.00"), savedSale.getSumPrice());
        }
        @Test
        void shouldThrowCrmClientNotFoundException(){
//            Given
            when(clientRepository.findCrmClientEntityById(clientId))
                    .thenReturn(Optional.empty());
//            When
            CrmClientNotFoundException ex = assertThrows(CrmClientNotFoundException.class,() -> saleService.createSale(createReq, userEntity.getName()));
//            Then
            verify(clientRepository, times(1)).findCrmClientEntityById(clientId);
            verifyNoInteractions(userRepository,stageRepository,saleRepository,saleItemRepository,cacheRepository);
            assertEquals(String.format("Client with id: %s not found",createReq.getClientId()),ex.getMessage());
            assertEquals("Err100",ex.getErrorCode());
        }

        @Test
        void shouldThrowCrmUserNotFoundException(){
//            Given
            when(clientRepository.findCrmClientEntityById(clientId))
                    .thenReturn(Optional.of(clientEntity));
            when(userRepository.findCrmUserEntityByMail(userEntity.getName()))
                    .thenReturn(Optional.empty());
//            When
            CrmUserNotFoundException ex = assertThrows(CrmUserNotFoundException.class,() -> saleService.createSale(createReq, userEntity.getName()));
//            Then
            verify(clientRepository, times(1)).findCrmClientEntityById(clientId);
            verify(userRepository, times(1)).findCrmUserEntityByMail(userEntity.getName());
            verifyNoInteractions(stageRepository,saleRepository,saleItemRepository,cacheRepository);
            assertEquals(String.format("User with id: %s not found",userEntity.getName()),ex.getMessage());
            assertEquals("USER_NOT_FOUND",ex.getErrorCode());

        }

        @Test
        void shouldThrowSaleStageNotFoundException(){
//            Given
            when(clientRepository.findCrmClientEntityById(clientId))
                    .thenReturn(Optional.of(clientEntity));
            when(userRepository.findCrmUserEntityByMail(userEntity.getName()))
                    .thenReturn(Optional.of(userEntity));
            when(stageRepository.findSaleStageByStage(any()))
                    .thenReturn(Optional.empty());
//            When
            SaleStageNotFoundException ex = assertThrows(SaleStageNotFoundException.class,() -> saleService.createSale(createReq, userEntity.getName()));
//            Then
            verify(clientRepository, times(1)).findCrmClientEntityById(clientId);
            verify(userRepository, times(1)).findCrmUserEntityByMail(userEntity.getName());
            verify(stageRepository, times(1)).findSaleStageByStage(any());
            verifyNoInteractions(saleRepository,saleItemRepository,cacheRepository);
            assertEquals("Internal error, sale stage not found",ex.getMessage());
            assertEquals("Err999",ex.getErrorCode());
        }

        @Test
        void shouldThrowProductCacheNotFoundException(){
//            Given
            when(clientRepository.findCrmClientEntityById(clientId))
                    .thenReturn(Optional.of(clientEntity));
            when(userRepository.findCrmUserEntityByMail(userEntity.getName()))
                    .thenReturn(Optional.of(userEntity));
            when(stageRepository.findSaleStageByStage(any()))
                    .thenReturn(Optional.of(stageEntity));
            when(cacheRepository.findProductStateById(any()))
                    .thenReturn(Optional.empty());
//            When
            ProductCacheNotFoundException ex = assertThrows(ProductCacheNotFoundException.class,() -> saleService.createSale(createReq, userEntity.getName()));
//            Then
            verify(clientRepository, times(1)).findCrmClientEntityById(clientId);
            verify(userRepository, times(1)).findCrmUserEntityByMail(userEntity.getName());
            verify(stageRepository, times(1)).findSaleStageByStage(any());
            verify(cacheRepository, times(1)).findProductStateById(any());
            verifyNoInteractions(saleRepository,saleItemRepository);
            assertEquals(String.format("Product with id: %s not found",prodStateId.toString()),ex.getMessage());
            assertEquals("Err102",ex.getErrorCode());
        }

        @Test
        void shouldThrowProductOutOfCacheException(){
//            Given
            stateEntity2.setProductState(BigDecimal.ZERO);
            when(clientRepository.findCrmClientEntityById(clientId))
                    .thenReturn(Optional.of(clientEntity));
            when(userRepository.findCrmUserEntityByMail(userEntity.getName()))
                    .thenReturn(Optional.of(userEntity));
            when(stageRepository.findSaleStageByStage(any()))
                    .thenReturn(Optional.of(stageEntity));
            when(cacheRepository.findProductStateById(prodStateId))
                    .thenReturn(Optional.of(stateEntity1));
            when(cacheRepository.findProductStateById(prodStateId2))
                    .thenReturn(Optional.of(stateEntity2));
//            When
            ProductOutOfStockException ex = assertThrows(ProductOutOfStockException.class,() -> saleService.createSale(createReq,userEntity.getName()));
//            Then
            verify(clientRepository, times(1)).findCrmClientEntityById(clientId);
            verify(userRepository, times(1)).findCrmUserEntityByMail(userEntity.getName());
            verify(stageRepository, times(1)).findSaleStageByStage(any());
            verify(cacheRepository, times(2)).findProductStateById(any());
            verifyNoInteractions(saleRepository,saleItemRepository);
            assertEquals("Err103",ex.getErrorCode());
            assertEquals(Map.of("available",stateEntity2.getProductState(), "expected",BigDecimal.TEN),ex.getMetadata());
        }

    }


}
