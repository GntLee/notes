----------------------------
v2ry						|
----------------------------
	# 地址
		https://www.v2ray.com
		https://github.com/v2ray/v2ray-core
	
		https://toutyrater.github.io/
		https://guide.v2fly.org/#%E5%A3%B0%E6%98%8E
	
----------------------------
官方安装					|
----------------------------
	# 下载并安装 V2Ray
		bash <(curl -L -s https://install.direct/go.sh)

		* yum 或 apt-get 可用的情况下,此脚本会自动安装 unzip 和 daemon
		* 这两个组件是安装 V2Ray 的必要组件
		* 如果系统不支持 yum 或 apt-get,请自行安装 unzip 和 daemon
	
		* 此脚本会自动安装以下文件
			/usr/bin/v2ray/v2ray		V2Ray 程序
			/usr/bin/v2ray/v2ctl		V2Ray 工具
			/etc/v2ray/config.json		配置文件
			/usr/bin/v2ray/geoip.dat	IP 数据文件
			/usr/bin/v2ray/geosite.dat	域名数据文件
		
		* 运行脚本位于系统的以下位置
			/etc/systemd/system/v2ray.service: Systemd
			/etc/init.d/v2ray: SysV
		
	
	# 编辑文件
		/etc/v2ray/config.json
	
	# 启动和维护
		systemctl start v2ray

		start
		stop
		status
		reload
		restart
		force-reload // 强制重新加载


	
	# 升级和更新
		* 再次下载脚本,重新安装就OK
	
	# 客户端的下载
		https://github.com/v2ray/v2ray-core/releases
	
	# 客户端命令
		v2ray info 
			查看 V2Ray 配置信息
		v2ray config 
			修改 V2Ray 配置
		v2ray link 
			生成 V2Ray 配置文件链接
		v2ray infolink 
			生成 V2Ray 配置信息链接
		v2ray qr 
			生成 V2Ray 配置二维码链接
		v2ray ss 
			修改 Shadowsocks 配置
		v2ray ssinfo 
			查看 Shadowsocks 配置信息
		v2ray ssqr 
			生成 Shadowsocks 配置二维码链接
		v2ray status 
			查看 V2Ray 运行状态
		v2ray start 
			启动 V2Ray
		v2ray stop 
			停止 V2Ray
		v2ray restart 
			重启 V2Ray
		v2ray log 
			查看 V2Ray 运行日志
		v2ray update 
			更新 V2Ray
		v2ray update.sh 
			更新 V2Ray 管理脚本
		v2ray uninstall 
			卸载 V2Ray
						

----------------------------
v2ray	命名行参数			|
----------------------------
	-version
		* 只输出当前版本然后退出,不运行 V2Ray 主程序



