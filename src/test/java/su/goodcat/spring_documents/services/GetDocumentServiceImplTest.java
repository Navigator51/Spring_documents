package su.goodcat.spring_documents.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import su.goodcat.spring_documents.domain.DictionaryRecord;
import su.goodcat.spring_documents.domain.DictionaryRecordsSearch;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class GetDocumentServiceImplTest {

    private static final DictionaryRecordsSearch DICTIONARY_RECORDS_SEARCH = new DictionaryRecordsSearch();
    private static final DictionaryRecordsSearch DICTIONARY_RECORDS_SEARCH_QUERY = new DictionaryRecordsSearch();
    private static final String SEARCH_QUERY = "До";
    private static final Integer SEARCH_RUBRIC_CODE = 16;
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

    @Test
    void method(){
        System.out.println("i'm method");
    }
}
