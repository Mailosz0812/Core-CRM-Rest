package org.mailosz.crmrest.sales;

import org.mailosz.crmrest.crmclient.ClientRepository;
import org.mailosz.crmrest.crmuser.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class SaleService {
    private final SaleRepository saleRepository;
    private final SaleItemRepository saleItemRepository;
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    public SaleService(SaleRepository saleRepository, SaleItemRepository saleItemRepository,
                       ClientRepository clientRepository, UserRepository userRepository) {
        this.saleRepository = saleRepository;
        this.saleItemRepository = saleItemRepository;
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
    }
}
