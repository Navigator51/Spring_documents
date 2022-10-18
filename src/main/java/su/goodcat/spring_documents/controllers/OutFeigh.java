package su.goodcat.spring_documents.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import su.goodcat.spring_documents.domain.DictionaryRecordsSearch;
import su.goodcat.spring_documents.domain.GetDictionaryRequest;

import javax.validation.Valid;
import javax.validation.constraints.Min;

public interface OutFeigh {
ResponseEntity<DictionaryRecordsSearch> apiV1DictionaryNameSearchPost(
                 @PathVariable("dictionaryName") String dictionaryName,
                 @Valid @RequestBody GetDictionaryRequest getDictionaryRequest,
         @Min(1)@Valid @RequestParam(value = "page", required = false) Integer page,
         @Min(1)@Valid @RequestParam(value = "size", required = false) Integer size);

        }