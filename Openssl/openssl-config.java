[ CA_default ]
dir             = /etc/pki/CA             # ����·������
certs           = $dir/certs              # �Ѱ䷢֤��ı���Ŀ¼
database        = $dir/index.txt          # ���ݿ������ļ�
new_certs_dir   = $dir/newcerts           # ��ǩ���֤�鱣��Ŀ¼
certificate     = $dir/cacert.pem         # CA֤��·����
serial          = $dir/serial             # ��ǰ֤�����к�
private_key     = $dir/private/cakey.pem  # CA��˽Կ·����