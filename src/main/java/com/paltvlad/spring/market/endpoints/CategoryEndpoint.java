package com.paltvlad.spring.market.endpoints;

import com.paltvlad.spring.market.services.CategoryService;
import com.paltvlad.spring.market.soap.categories.GetCategoryByTitleRequest;
import com.paltvlad.spring.market.soap.categories.GetCategoryByTitleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class CategoryEndpoint {
    private static final String NAMESPACE_URI = "http://www.paltvlad.com/spring/market/categories";
    private final CategoryService categoryService;

    /*
        Пример запроса: POST http://localhost:8189/ws

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
          xmlns:f="http://www.paltvlad.com/spring/market/categories">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getGroupByTitleRequest>
                    <f:title>Food</f:title>
                </f:getGroupByTitleRequest>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCategoryByTitleRequest")
    @ResponsePayload
    @Transactional
    public GetCategoryByTitleResponse getGroupByTitle(@RequestPayload GetCategoryByTitleRequest request) {
        GetCategoryByTitleResponse response = new GetCategoryByTitleResponse();
        response.setCategory(categoryService.getByTitle(request.getTitle()));
        return response;
    }
}
