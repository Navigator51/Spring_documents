package su.goodcat.spring_documents.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import su.goodcat.spring_documents.domain.DictionaryRecordsSearch;
import su.goodcat.spring_documents.domain.GetDictionaryRequest;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@FeignClient(name = "spr-fuck", url = "${property.spring-application.url}")
public interface OutFeigh {

    @RequestMapping(path = "/api/v1/DictionaryNameSearchPost/", method = RequestMethod.POST)
    ResponseEntity<DictionaryRecordsSearch> apiV1DictionaryNameSearchPost(
            @Valid @RequestBody GetDictionaryRequest getDictionaryRequest,
            @PathVariable("dictionaryName") String dictionaryName,
            @Min(1) @Valid @RequestParam(value = "page", required = false) Integer page,
            @Min(1) @Valid @RequestParam(value = "size", required = false) Integer size);

}