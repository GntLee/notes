-------------------------
Auditing
-------------------------
	# һ��һ����¼������: �����û�, ����ʱ��, �޸��û�, �޸�ʱ��ȵ���Ϣ,
		* spring-data-jpa �ṩһϵ�з����ע��

	# ��ʵ�������ϱ�ʶ���ע��
		@CreatedBy
		@CreatedDate
		@LastModifiedBy
		@LastModifiedDate

		@CreatedBy
		@Column(name = "created_by", columnDefinition = "INT(11) UNSIGNED NOT NULL COMMENT '�����û�'")
		private Integer createdBy;
		
		@CreatedDate
		@Column(name = "created_date", columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��'")
		private LocalDateTime createdDate;
		
		@LastModifiedBy
		@Column(name = "last_modified_by", columnDefinition = "INT(11) UNSIGNED COMMENT '����޸��û�'")
		private Integer lastModifiedBy;
		
		@LastModifiedDate
		@Column(name = "last_modified_date", columnDefinition = "timestamp NULL DEFAULT NULL COMMENT '����޸�ʱ��'")
		private LocalDateTime lastModifiedDate;

	# ��ʵ�����ϱ�ʶע��ע��
		@EntityListeners
			|- Class[] value();
				* ָ��������
		
		@EntityListeners(value = { AuditingEntityListener.class })
	
	# ʵ��: AuditorAware �ӿ�, ����ϵͳ��ȡ��ǰ�û�id
		public interface AuditorAware<T> {
			Optional<T> getCurrentAuditor();
		}

		import java.util.Optional;
		import javax.servlet.http.HttpSession;
		import org.springframework.data.domain.AuditorAware;
		import org.springframework.stereotype.Component;
		import org.springframework.web.context.request.RequestContextHolder;
		import org.springframework.web.context.request.ServletRequestAttributes;

		@Component
		public class SessionUserIdAuditorAware implements AuditorAware<Integer>{

			@Override
			public Optional<Integer> getCurrentAuditor() {
				ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
				HttpSession session = servletRequestAttributes.getRequest().getSession(false);
				return session == null ? Optional.empty() : Optional.of((Integer)session.getAttribute("session_user_id"));
			}
		}

		* �ýӿڷ��ض���Ĺ���id
	
	# ͨ��ע�⿪ʼ jpa-auditing
		@EnableJpaAuditing
			|-String auditorAwareRef() default "";
			|-boolean setDates() default true;
			|-boolean modifyOnCreate() default true;
			|-tring dateTimeProviderRef() default "";
		