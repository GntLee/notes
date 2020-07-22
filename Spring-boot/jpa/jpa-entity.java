------------------
entity
------------------
	# ʵ���������ؼ�ע��
		@Entity		��EntityManage����, �����ںܶ�ط�ʹ����д
		@Table		��Ҫӳ���
	
------------------
Persistable
------------------
	# һ�����ڱ�ʶʵ�����ǲ����µ�ʵ����Ľӿ�
	# ��ѵ�ʵ��, ���ǹ����ĸ���ʵ�ָýӿ�, �������ûص�����, ���޸Ķ����״̬
		@MappedSuperclass
		public abstract class AbstractBaseEntity<ID> implements Persistable<ID> {

			@Transient
			private boolean isNew = true; 

			@Override
			public boolean isNew() {
				return isNew; 
			}

			@PrePersist			// �ڴ洢֮ǰִ��
			@PostLoad			// �ڼ���֮��ִ��
			void markNotNew() {
				this.isNew = false;
			}
			// More code��
		}

------------------
EntityInformation
------------------