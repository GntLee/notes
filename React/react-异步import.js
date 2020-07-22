----------------------------
�첽import
----------------------------
	# ʹ�� React.lazy ����������ģ��
		const OtherComponent = React.lazy(() => import('./OtherComponent'));
	
		* ���������״���Ⱦ��ʱ��, �Żᱻ����
		* React.lazy ����һ������, ���������Ҫ��̬���� import()
		* ����������뷵��һ�� Promise, �� Promise ��Ҫ resolve һ�� defalut export �� React ���

	# �� Suspense �������Ⱦ lazy ���
		import React, { Suspense } from 'react';
		// �첽�ĵ������
		const OtherComponent = React.lazy(() => import('./OtherComponent'));
		function MyComponent() {
		  return (
			<div>
			  <Suspense fallback={<div>Loading...</div>}>
				<OtherComponent />
			  </Suspense>
			</div>
		  );
		}

		* ����ͨ�� fallback �����ȴ����� lazy ���ʱ�����Ž���(�� loading ָʾ����)
		*  Suspense ����������������֮�ϵ��κ�λ��, ����������һ�� Suspense �������������������
	
		* ���ģ�����ʧ��(����������), ���ᴥ��һ������, ����ͨ��������쳣�������������


	
	# React.lazy Ŀǰֻ֧��Ĭ�ϵ���(default exports)
		* ��Ҫ�ӳټ��ط�Ĭ�ϵ�����ģ��, ����ͨ���½�һ���м�ģ�������
		// ManyComponents.js
		export const MyComponent = /* ... */;		// ����ģ��
		export const MyUnusedComponent = /* ... */;

		// MyComponent.js �м�ģ�飬��������ģ�飬������Ĭ��ģ��ķ�ʽ��¶��ȥ
		export { MyComponent as default } from "./ManyComponents.js";

		// MyApp.js
		import React, { lazy } from 'react';
		const MyComponent = lazy(() => import("./MyComponent.js")); // �ӳټ���Ĭ���м�ģ���е�Ĭ��ģ��


		* ���ܱ�֤ tree shaking �������, ���Ҳ������벻��Ҫ�����
