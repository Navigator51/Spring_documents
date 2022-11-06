package su.goodcat.spring_documents.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessagesConstants {
    public static final String BAD_VALUES_FORMAT_ERROR = "[SPRDOC-E001] Неверный формат ввода данных";
    public static final String BAD_DOCUMENT_STATUS_ERROR = "[SPRDOC-E002] Недопустимый статус документа в запросе";
}
