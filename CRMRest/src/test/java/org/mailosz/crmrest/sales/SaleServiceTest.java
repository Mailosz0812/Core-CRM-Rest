package org.mailosz.crmrest.sales;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mailosz.crmrest.crmclient.ClientRepository;
import org.mailosz.crmrest.crmclient.CrmClientEntity;
import org.mailosz.crmrest.crmuser.CrmUserEntity;
import org.mailosz.crmrest.crmuser.UserRepository;
import org.mailosz.crmrest.crmuser.roles.RoleEntity;
import org.mailosz.crmrest.exception.types.*;
import org.mailosz.crmrest.prices.PriceListEntity;
import org.mailosz.crmrest.prices.SellingUnit;
import org.mailosz.crmrest.product.Category;
import org.mailosz.crmrest.product.ProductCacheRepository;
import org.mailosz.crmrest.product.ProductEntity;
import org.mailosz.crmrest.product.ProductState;
import org.mailosz.crmrest.sales.request.CustomSaleItem;
import org.mailosz.crmrest.sales.request.SaleCreateReq;
import org.mailosz.crmrest.sales.request.SaleItemReq;
import org.mailosz.crmrest.sales.response.SaleCreationResp;
import org.mailosz.crmrest.sales.response.SaleItemResponse;
import org.mailosz.crmrest.sales.response.SaleResponse;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

    @Captor
    private ArgumentCaptor<List<SaleItem>> saleItemArgumentCaptor;

    @InjectMocks
    private SaleService saleService;

    @Nested
    @DisplayName("Sale creation unit tests")
    class createSaleTests{
        SaleCreateReq createReq;
        SaleCreateReq createReq2;
        CrmClientEntity clientEntity;
        CrmUserEntity userEntity;
        SaleStage stageEntity;
        SaleEntity saleEntityRes;
        ProductEntity productEntity;
        ProductEntity productEntity1;
        List<ProductEntity> productsList;
        List<SaleItem> saleItemsRes;
        SaleCreationResp expectedSaleResponse;
        PriceListEntity priceList;

        UUID prodId = UUID.randomUUID();
        UUID prodId2 = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        UUID stageId = UUID.randomUUID();
        UUID itemId = UUID.randomUUID();
        UUID itemId2 = UUID.randomUUID();
        UUID saleId = UUID.randomUUID();
        UUID priceListId = UUID.randomUUID();

        @BeforeEach
        void setUp() {
            List<SaleItemReq> saleItems = new ArrayList<>(List.of(
                    new SaleItemReq(prodId,BigDecimal.ONE),
                    new SaleItemReq(prodId2,BigDecimal.ONE)
            ));
            List<CustomSaleItem> customItems = Collections.emptyList();

            createReq = new SaleCreateReq(
                    clientId,
                    saleItems,
                    customItems,
                    "dummy data",
                    "dummy data",
                    "dummy name"
                    );

            clientEntity = new CrmClientEntity(
                    clientId, "test",
                    "testnip", "test", "testphone",
                    "mail@test.com", "dummy person");

            RoleEntity role = new RoleEntity();
            userEntity = new CrmUserEntity(
                    userId, "mail@test.com",
                    "testPass", role, "test-name", "test-surname");

            stageEntity = new SaleStage(stageId, Stage.NOWA);
            priceList = new PriceListEntity(
                    priceListId,
                    OffsetDateTime.now(),
                    productsList
            );
            productEntity = new ProductEntity(
                    prodId,
                    BigDecimal.TEN,
                    "test name","internal test",
                    null,true,
                    priceList,
                    Category.INNE,
                    SellingUnit.KARTON);
            productEntity1 = new ProductEntity(
                    prodId2,
                    BigDecimal.TEN,
                    "test name","internal test",
                    null,true,
                    priceList,
                    Category.INNE,
                    SellingUnit.KARTON);
            productsList = new ArrayList<>(List.of(productEntity, productEntity1));
            saleItemsRes = new ArrayList<>(List.of(
                    new SaleItem(itemId, saleEntityRes, productEntity,
                            productEntity.getProductName(),productEntity.getInternalName(),
                            productEntity.getUnitPrice(), BigDecimal.ONE,BigDecimal.TEN,productEntity.getUnit()),
                    new SaleItem(itemId2, saleEntityRes, productEntity1,
                            productEntity1.getProductName(),productEntity1.getInternalName(),
                            productEntity1.getUnitPrice(), BigDecimal.ONE,BigDecimal.TEN,productEntity1.getUnit())
            ));

            saleEntityRes = new SaleEntity(
                    saleId,
                    clientEntity,
                    userEntity,
                    stageEntity,
                    saleItemsRes,
                    OffsetDateTime.now(),
                    OffsetDateTime.now(),
                    null,
                    BigDecimal.TEN,
                    "dummy data",
                    "dummy data",
                    null,
                    "dummy name");
            BigDecimal total = saleItemsRes.stream().map(SaleItem::getSumPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
            saleEntityRes.setSumPrice(total);

            List<SaleItemResponse> itemsResp = List.of(
                    new SaleItemResponse(
                            itemId.toString(),
                            prodId.toString(),
                            productEntity.getProductName(),
                            productEntity.getUnitPrice(),
                            productEntity.getUnit(),
                            BigDecimal.ONE,
                            BigDecimal.TEN,
                            productEntity.getInternalName()
                    ),
                    new SaleItemResponse(
                            itemId.toString(),
                            prodId.toString(),
                            productEntity1.getProductName(),
                            productEntity1.getUnitPrice(),
                            productEntity1.getUnit(),
                            BigDecimal.ONE,
                            BigDecimal.TEN,
                            productEntity1.getInternalName()
                    )
            );

            expectedSaleResponse = new SaleCreationResp(
                    saleId.toString(),
                    createReq.getSaleData(),
                    createReq.getWarehouseNote(),
                    itemsResp,
                    saleEntityRes.getStage(),
                    saleEntityRes.getSumPrice(),
                    saleEntityRes.getSaleName(),
                    saleEntityRes.getClient().getId().toString(),
                    saleEntityRes.getClient().getName(),
                    saleEntityRes.getCreatedAt()
                    );
        }
        @Test
        void shouldCreateSaleSuccessfully(){
//            Given
            when(clientRepository.findCrmClientEntityById(clientId))
                    .thenReturn(Optional.of(clientEntity));
            when(userRepository.findCrmUserEntityByMail(userEntity.getName()))
                    .thenReturn(Optional.of(userEntity));
            when(stageRepository.findSaleStageByStage(Stage.NOWA))
                    .thenReturn(Optional.of(stageEntity));
            when(saleRepository.save(any()))
                    .thenReturn(saleEntityRes);
            when(saleItemRepository.saveAll(any()))
                    .thenReturn(saleItemsRes);
            when(cacheRepository.findAllById(any()))
                    .thenReturn(productsList);
//            When
            SaleCreationResp resp = saleService.createSale(createReq, userEntity.getName());
//            Then
            verify(clientRepository,times(1)).findCrmClientEntityById(clientId);
            verify(userRepository,times(1)).findCrmUserEntityByMail(userEntity.getName());
            verify(stageRepository,times(1)).findSaleStageByStage(Stage.NOWA);

//            Database related checks
            verify(saleRepository).save(saleEntityCaptor.capture());
            SaleEntity savedEntity = saleEntityCaptor.getValue();
            verify(saleItemRepository).saveAll(saleItemArgumentCaptor.capture());
            List<SaleItem> savedItems = saleItemArgumentCaptor.getValue();

//            SaleEntity asserts
            assertEquals(new BigDecimal("20.00"),savedEntity.getSumPrice());
            assertEquals(clientId,savedEntity.getClient().getId());
            assertEquals(userId,savedEntity.getUser().getId());
            assertEquals(Stage.NOWA,savedEntity.getStage());

//            SaleItems asserts
            assertEquals(2,savedItems.size());
            SaleItem saleItem = savedItems.get(0);
            assertEquals(productEntity.getUnitPrice(), saleItem.getUnitPriceAtSale());
            assertEquals(new BigDecimal("10.00"),saleItem.getSumPrice());

//            Response asserts
            assertEquals(saleId.toString(), resp.getSaleId());
            assertEquals(savedEntity.getSumPrice(),resp.getSumPrice());
            assertEquals(createReq.getSaleData(),resp.getSaleData());
            assertEquals(createReq.getSaleName(),resp.getSaleName());
            assertEquals(createReq.getWarehouseNote(),resp.getWarehouseNote());
        }

        @Test
        void shouldThrowCrmClientNotFoundException(){
//            Given
            when(clientRepository.findCrmClientEntityById(any()))
                    .thenReturn(Optional.empty());
//            When
            CrmClientNotFoundException ex = assertThrows(CrmClientNotFoundException.class,() -> saleService.createSale(createReq, userEntity.getName()));
//            Then
            verify(clientRepository,times(1)).findCrmClientEntityById(any());
            verifyNoInteractions(userRepository,stageRepository,saleRepository,saleItemRepository,cacheRepository);
            assertEquals(String.format("Client with id: %s not found",clientId),ex.getMessage());
            assertEquals("CLIENT_NOT_FOUND",ex.getErrorCode());
            assertEquals(HttpStatus.NOT_FOUND,ex.getStatus());
        }

        @Test
        void shouldThrowCrmUserNotFoundException(){
//            Given
            when(clientRepository.findCrmClientEntityById(any()))
                    .thenReturn(Optional.of(clientEntity));
            when(userRepository.findCrmUserEntityByMail(any()))
                    .thenReturn(Optional.empty());
//            When
            CrmUserNotFoundException ex = assertThrows(CrmUserNotFoundException.class,() -> saleService.createSale(createReq, userEntity.getName()));
//            Then
            verify(clientRepository,times(1)).findCrmClientEntityById(any());
            verify(userRepository,times(1)).findCrmUserEntityByMail(any());
            verifyNoInteractions(stageRepository,saleRepository,saleItemRepository,cacheRepository);

            assertEquals(String.format("User with id: %s not found",userEntity.getName()),ex.getMessage());
            assertEquals("USER_NOT_FOUND",ex.getErrorCode());
            assertEquals(HttpStatus.NOT_FOUND,ex.getStatus());
        }

        @Test
        void shouldThrowSaleStageNotFound(){
//            Given
            when(clientRepository.findCrmClientEntityById(any()))
                    .thenReturn(Optional.of(clientEntity));
            when(userRepository.findCrmUserEntityByMail(any()))
                    .thenReturn(Optional.of(userEntity));
            when(stageRepository.findSaleStageByStage(any()))
                    .thenReturn(Optional.empty());

//            When
            SaleStageNotFoundException ex = assertThrows(SaleStageNotFoundException.class,() -> saleService.createSale(createReq, userEntity.getName()));
//            Then
            verify(clientRepository,times(1)).findCrmClientEntityById(any());
            verify(userRepository,times(1)).findCrmUserEntityByMail(any());
            verify(stageRepository,times(1)).findSaleStageByStage(Stage.NOWA);
            verifyNoInteractions(saleRepository,saleItemRepository,cacheRepository);

            assertEquals("Internal error, sale stage not found",ex.getMessage());
            assertEquals("INTERNAL_ERROR",ex.getErrorCode());
        }

        @Test
        void shouldThrowProductNotFoundException(){
//            Given
            UUID id = UUID.randomUUID();
            List<SaleItemReq> notFoundItems = new ArrayList<>(List.of(
                    new SaleItemReq(id,BigDecimal.ONE),
                    new SaleItemReq(prodId2,BigDecimal.ONE)
            ));
            createReq2 = new SaleCreateReq(
                    clientId,
                    notFoundItems,
                    Collections.emptyList(),
                    "dummy data",
                    "dummy data",
                    "dummy name"
            );
            when(clientRepository.findCrmClientEntityById(clientId))
                    .thenReturn(Optional.of(clientEntity));
            when(userRepository.findCrmUserEntityByMail(userEntity.getName()))
                    .thenReturn(Optional.of(userEntity));
            when(stageRepository.findSaleStageByStage(Stage.NOWA))
                    .thenReturn(Optional.of(stageEntity));
            when(cacheRepository.findAllById(any()))
                    .thenReturn(productsList);
//            When
            ProductNotFoundException ex = assertThrows(ProductNotFoundException.class,() -> saleService.createSale(createReq2, userEntity.getName()));
//            Then
            verify(clientRepository,times(1)).findCrmClientEntityById(any());
            verify(userRepository,times(1)).findCrmUserEntityByMail(any());
            verify(cacheRepository,times(1)).findAllById(any());
            verifyNoInteractions(saleRepository,saleItemRepository);

            assertEquals(String.format("Product with id: %s not found in price list",id),ex.getMessage());
            assertEquals("PROD_NOT_FOUND",ex.getErrorCode());
            assertEquals(HttpStatus.NOT_FOUND,ex.getStatus());
        }

    }


}
