package com.paltvlad.spring.market.endpoints;


import com.paltvlad.spring.market.services.ProductService;
import com.paltvlad.spring.market.soap.products.GetAllProductsRequest;
import com.paltvlad.spring.market.soap.products.GetAllProductsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.paltvlad.com/spring/market/products";
    private final ProductService productService;
//
//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByTitleRequest")
//    @ResponsePayload
//    public GetProductByTitleResponse getProductByTitle(@RequestPayload GetProductByTitleRequest request) {
//        GetProductByTitleResponse response = new GetProductByTitleResponse();
//        response.setProduct(productService.getByTitle(request.getTitle()));
//        return response;
//    }

    /*
        Пример запроса: POST http://localhost:8189/ws
        Header -> Content-Type: text/xml

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.paltvlad.com/spring/market/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getAllProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productService.getAllProductsSoap().forEach(response.getProducts()::add);
        return response;
    }
}
