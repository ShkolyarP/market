package com.paltvlad.market.core.endpoints;

import com.paltvlad.market.core.soap.categories.GetCategoryByTitleRequest;
import com.paltvlad.market.core.soap.categories.GetCategoryByTitleResponse;
import com.paltvlad.market.core.services.CategoryService;
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
                <f:getCategoryByTitleRequest>
                    <f:title>Food</f:title>
                </f:getCategoryByTitleRequest>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCategoryByTitleRequest")
    @ResponsePayload
    @Transactional
    public GetCategoryByTitleResponse getCategoryByTitle(@RequestPayload GetCategoryByTitleRequest request) {
        GetCategoryByTitleResponse response = new GetCategoryByTitleResponse();
        response.setCategory(categoryService.getByTitle(request.getTitle()));
        return response;
    }
}
