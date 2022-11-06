package su.goodcat.spring_documents.domain;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;

import su.goodcat.commonlib.domain.Status;

import javax.persistence.*;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@TypeDef(name = "postgresEnum", typeClass = PostgreSQLEnumType.class)
@Table(schema = "biplan")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "creator_id")
    private Long creatorId;

    @Column(name = "sender_id")
    private Long senderId;

    @ElementCollection
    @Column(name = "recipient_id")
    @CollectionTable(schema = "biplan", name = "document_recipient",
            joinColumns = @JoinColumn(name = "document_id"))
    private List<Long> recipientsId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    private List<File> fileList;

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @UpdateTimestamp
    @Column(name = "modify_date")
    private LocalDateTime modifyDate;

    // объявление типа данных для базы
    @Type(type = "postgresEnum")
    // запись енама в базу по значению
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "was_read")
    private boolean isRead;
}
