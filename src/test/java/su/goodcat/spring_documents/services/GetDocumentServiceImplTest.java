package su.goodcat.spring_documents.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import su.goodcat.spring_documents.feign.OutFeigh;
import su.goodcat.spring_documents.domain.*;
import su.goodcat.spring_documents.services.impl.GetDocumentServiceImpl;

import java.util.*;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetDocumentServiceImplTest {

    private static final DictionaryRecordsSearch DICTIONARY_RECORDS_SEARCH = new DictionaryRecordsSearch();
    private static final DictionaryRecordsSearch DICTIONARY_RECORDS_SEARCH_QUERY = new DictionaryRecordsSearch();

    private static final String SBER_DOCUMENTS = "документы с ПАО Сбербанк";
    private static final String COUNTERPARTY_DOCUMENTS = "документы с контрагентами";

    private static DictionaryRecord prepareNsiDocumentTypeItem(
            String documentTypeCode, String documentTypeName,
            Integer rubricCode, String rubricName,
            Integer categoryCode, String categoryName
    ) {
        LinkedHashMap<String, Object> documentTypeItem = new LinkedHashMap<>(
                Map.of(
                        "isStructured", Boolean.FALSE,
                        "changingSignatureRequirementsBan", Boolean.FALSE,
                        "senderSignatureRequired", Boolean.FALSE,
                        "receiverSignatureRequired", Boolean.FALSE,
                        "documentTypeCode", documentTypeCode,
                        "documentTypeName", documentTypeName,
                        "documentTypeGroup", Map.of("data", Map.of("documentTypeGroupCode", rubricCode, "documentTypeGroupName", rubricName)),
                        "documentTypeCategory", Map.of("data", Map.of("documentTypeCategoryCode", categoryCode, "documentTypeCategoryName", categoryName))
                )
        );
        return new DictionaryRecord().setData(documentTypeItem);
    }

    @BeforeAll
    static void initAll() {
        DICTIONARY_RECORDS_SEARCH_QUERY.addContentItem(prepareNsiDocumentTypeItem(
                "AGREEMENT", "Договор", 7, "Договорная документация", 1, SBER_DOCUMENTS));
        DICTIONARY_RECORDS_SEARCH_QUERY.addContentItem(prepareNsiDocumentTypeItem(
                "AGREEMENT", "Договор", 16, "Прочие", 2, COUNTERPARTY_DOCUMENTS));
        DICTIONARY_RECORDS_SEARCH_QUERY.addContentItem(prepareNsiDocumentTypeItem(
                "AGREEMENT", "Договор", 3, "Акты", 1, SBER_DOCUMENTS));
        DICTIONARY_RECORDS_SEARCH_QUERY.addContentItem(prepareNsiDocumentTypeItem(
                "COURIER_AGREEMENT_FACTDEB", "Документ факторинг (дебитор)", 16, "Прочие", 2, COUNTERPARTY_DOCUMENTS));
        DICTIONARY_RECORDS_SEARCH_QUERY.addContentItem(prepareNsiDocumentTypeItem(
                "AGREEMENT_RETC", "Документ об оказании услуг", 16, "Прочие", 2, COUNTERPARTY_DOCUMENTS));
        DICTIONARY_RECORDS_SEARCH.setContent(new ArrayList<>(DICTIONARY_RECORDS_SEARCH_QUERY.getContent()));
        DICTIONARY_RECORDS_SEARCH.addContentItem(prepareNsiDocumentTypeItem(
                "VERIFICATION", "Акт сверки", 3, "Акты", 1, SBER_DOCUMENTS));
        DICTIONARY_RECORDS_SEARCH.addContentItem(prepareNsiDocumentTypeItem(
                "CREDIT_REQUEST_REPLY", "Ответ на запрос", 12, "Кредитный процесс", 1, SBER_DOCUMENTS));
        DICTIONARY_RECORDS_SEARCH.addContentItem(prepareNsiDocumentTypeItem(
                "AGENTREPORT", "Отчёт комиссионера", 16, "Прочие", 2, COUNTERPARTY_DOCUMENTS));
        DICTIONARY_RECORDS_SEARCH.addContentItem(prepareNsiDocumentTypeItem(
                "EXPLANATORY_NOTE", "Пояснительная записка", 16, "Прочие", 2, COUNTERPARTY_DOCUMENTS));
        DICTIONARY_RECORDS_SEARCH.addContentItem(prepareNsiDocumentTypeItem(
                "PRICAT", "Прайс-лист", 16, "Прочие", 2, COUNTERPARTY_DOCUMENTS));
    }

    private GetDocumentServiceImpl rubricatorService;
    @Mock
    private OutFeigh outFeigh;

    @BeforeEach
    void init() {
        rubricatorService = new GetDocumentServiceImpl(outFeigh);
    }


    @Test
    @DisplayName("Получение всех категорий с рубриками и типами с сортировкой по алфавиту")
    void getCategoriesTest() {
        // given
        when(outFeigh.apiV1DictionaryNameSearchPost(any(), any(), any(), any())).thenReturn(ResponseEntity.ok(DICTIONARY_RECORDS_SEARCH));

        //when
        List<Category> result = rubricatorService.getCategoryList();


// then
        assertThat(result).hasSize(2);
        assertThat(result).isSortedAccordingTo(Comparator.comparing(Category::getTypeCategoryName));
        assertThat(result.get(1).getGroupList()).hasSize(1);
        assertThat(result.get(0).getGroupList()).hasSize(3);
        assertThat(result.get(1).getGroupList()).isSortedAccordingTo(Comparator.comparing(Group::getGroupeName));
        assertThat(result.get(1).getGroupList().get(0).getGroupeCode()).isEqualTo(16);
        assertThat(result.get(1).getGroupList().get(0).getTypeList())
                .isSortedAccordingTo(Comparator.comparing(Type::getTypeName));
        assertThat(result.get(0).getGroupList()).satisfiesExactly(
                rubric -> assertThat(rubric.getTypeList()).hasSize(2),
                rubric -> assertThat(rubric.getTypeList()).hasSize(1),
                rubric -> assertThat(rubric.getTypeList()).hasSize(1)
        );
        assertThat(result.get(0).getGroupList().get(1).getTypeList())
                .isSortedAccordingTo(Comparator.comparing(Type::getTypeName));
    }
}
