-----------------------
http2
-----------------------

-----------------------
����ʹ��push
-----------------------
	# push
		http.HandleFunc("/", func(w http.ResponseWriter, r *http.Request) {
			// ���ʵ����http.Pusher�ӿڣ��Ϳ���ʹ��
			if pusher, ok := w.(http.Pusher); ok {
				// Push is supported.
				if err := pusher.Push("/app.js", nil); err != nil {
					log.Printf("Failed to push: %v", err)
				}
			}
			// ...
		})
	
	# type PushOptions struct {
			Method string
			Header Header
		}
		* ָ��Push�ķ�����header
	
	# type Pusher interface {
			Push(target string, opts *PushOptions) error
		}

		* http2��push�ӿ�