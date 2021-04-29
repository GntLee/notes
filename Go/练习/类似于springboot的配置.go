---------------------------
����
---------------------------
server:
  port: 80
  host: "0.0.0.0"
  read-timeout: "0s"
  read-header-timeout: "0s"
  write-timeout: "0s"
  idle-timeout: "0s"
  max-header-bytes: 0
  ssl:
    enabled: false
    cert-file: ""
    key-file: ""
  compression:
    enabled: true
  http:
    max-request-body: 10240000
    max-multipart-memory: 1024000

datasource:
  type: "mysql"
  url: "root:root@tcp(localhost:3306)/rose?charset=utf8mb4&parseTime=True&loc=Local"
  pool:
    max-idle-time: "5m"
    max-lifetime: "30m"
    max-idle-conns: 10
    max-open-conns: 50

redis:
  url: "localhost:6379"
  password: ""
  db: 0
  connect-timeout: "0s"
  read-timeout: "0s"
  write-timeout: "0s"
  pool:
    max-idle: 10
    max-active: 50
    idle-timeout: "5m"
    conn-max-idle-Time: "5m"
    max-conn-lifetime: "30m"
    wait: true

---------------------------
���ü���
---------------------------
package config

import (
	"flag"
	"fmt"
	"github.com/go-yaml/yaml"
	"log"
	"os"
	"time"
)

const (
	DefaultConfigFile = "./app.yaml"
)

// CompressionConfig ��Ӧѹ������
type CompressionConfig struct {
	Enabled bool 		`yaml:"enabled"`
	//Deprecated
	MinResponseSize int `yaml:"min-response-size"`
	//Deprecated
	MineTypes []string 	`yaml:"mime-types"`
}

// SslConfig ����
type SslConfig struct {
	Enabled bool 	`yaml:"enabled"`
	CertFile string	`yaml:"cert-file"`
	KeyFile string	`yaml:"key-file"`
}

// HttpConfig http����
type HttpConfig struct {
	MaxMultipartMemory int64		`yaml:"max-multipart-memory"`
	MaxRequestBody int64			`yaml:"max-request-body"`
}

// ServerConfig ����������
type ServerConfig struct {
	Port int						`yaml:"port"`
	Host string						`yaml:"host"`
	ReadTimeout time.Duration		`yaml:"read-timeout"`
	ReadHeaderTimeout time.Duration `yaml:"read-header-timeout"`
	WriteTimeout time.Duration		`yaml:"write-timeout"`
	IdleTimeout time.Duration 		`yaml:"idle-timeout"`
	MaxHeaderBytes int 				`yaml:"max-header-bytes"`
	Ssl *SslConfig					`yaml:"ssl"`
	Compression *CompressionConfig	`yaml:"compression"`
	Http *HttpConfig				`yaml:"http"`
}

// DataSourceConfig ����Դ����
type DataSourceConfig struct {
	Type string `yaml:"type"`
	Url string	`yaml:"url"`
	Pool *struct {
		MaxIdleTime   time.Duration		`yaml:"max-idle-time"`
		MaxLifetime   time.Duration		`yaml:"max-lifetime"`
		MaxIdleConns  int				`yaml:"max-idle-conns"`
		MaxOpenConns  int				`yaml:"max-open-conns"`
	}	`yaml:"pool"`
}

// RedisConfig Redis����
type RedisConfig struct {
	Url string			`yaml:"url"`
	Password string		`yaml:"password"`
	Db int				`yaml:"db"`
	ConnectTimeout	time.Duration	`yaml:"connect-timeout"`
	ReadTimeout time.Duration		`yaml:"read-timeout"`
	WriteTimeout time.Duration		`yaml:"write-timeout"`
	Pool *struct{
		MaxIdle int 	`yaml:"max-idle"`
		MaxActive int 	`yaml:"max-active"`
		MaxConnLifetime time.Duration	`yaml:"max-conn-lifetime"`
		IdleTimeout time.Duration		`yaml:"idle-timeout"`
		Wait bool		`yaml:"wait"`
	} `yaml:"pool"`

}

// AppConfig Ӧ������
type AppConfig struct {
	Server *ServerConfig 			`yaml:"server"`
	Datasource *DataSourceConfig	`yaml:"datasource"`
	Redis *RedisConfig				`yaml:"redis"`
}
func (a AppConfig) String () string {
	if data, err := yaml.Marshal(a); err != nil {
		return fmt.Sprintf("Yaml�����쳣��%s", err.Error())
	} else {
		return string(data)
	}
}

// App ȫ��Ψһ����
var App = new(AppConfig)

func Init (){

	var configFilePath = flag.String("config", DefaultConfigFile, "�����ļ�·��")
	flag.Parse()

	log.Printf("���������ļ���%s\n", *configFilePath)

	if configFile, err := os.ReadFile(*configFilePath); err != nil {
		log.Fatalf("��ȡ�����ļ������쳣��%s\n", err.Error())
	} else {
		if err := yaml.Unmarshal(configFile, App); err != nil {
			log.Fatalf("����������Ϣ�쳣��%s\n", err.Error())
		}
	}

	log.Printf("������Ϣ��\n %s \n", App)
}
---------------------------
DB & Redis
---------------------------
import (
	"fmt"
	"gorm.io/driver/mysql"
	"gorm.io/driver/sqlite"
	"gorm.io/gorm"
	"gorm.io/gorm/logger"
	"gorm.io/gorm/schema"
	"log"
	"rose/config"
	"rose/model"
	"strings"
	"time"
)

var ORM *gorm.DB

var Logger = logger.New(log.Default(), logger.Config{
	SlowThreshold:             200 * time.Millisecond,
	Colorful:                  true,
	IgnoreRecordNotFoundError: false,
	LogLevel:                  logger.Warn,
})

func Init() error {

	var dbConfig = config.App.Datasource

	var dialect gorm.Dialector
	if strings.EqualFold(dbConfig.Type, "mysql") {
		dialect = mysql.Open(dbConfig.Url)
	}  else if strings.EqualFold(dbConfig.Type, "sqlite") {
		dialect = sqlite.Open(dbConfig.Url)
	}
	if dialect == nil {
		return fmt.Errorf("δ֪������Դ���ͣ�%s\n", dbConfig.Type)
	}

	gormDB, err := gorm.Open(dialect,
		&gorm.Config{
			NamingStrategy: schema.NamingStrategy {
				SingularTable: true,		// �����Ƶ���
			},
			Logger: Logger,					// ��־��¼
			QueryFields: false,				// ����ʱ�г�������
			PrepareStmt: true,				// ����Ԥ����SQL
		})
	if err != nil {
		return err
	}

	// ����Դ����
	rawDB, err := gormDB.DB()
	if err != nil {
		return err
	}
	rawDB.SetMaxIdleConns(dbConfig.Pool.MaxIdleConns)
	rawDB.SetMaxOpenConns(dbConfig.Pool.MaxOpenConns)
	rawDB.SetConnMaxIdleTime(dbConfig.Pool.MaxIdleTime)
	rawDB.SetConnMaxLifetime(dbConfig.Pool.MaxLifetime)

	ORM = gormDB

	return nil
}

func AutoMigrate () error {
	return ORM.AutoMigrate(model.Models...)
}

func Shutdown() error {
	rawDb, err := ORM.DB()
	if err != nil {
		return err
	}
	return rawDb.Close()
}


package redis

import (
	"context"
	"errors"
	redigo "github.com/gomodule/redigo/redis"
	"rose/config"
	"time"
)

var Pool *redigo.Pool

func Init (){
	var redisConfig = config.App.Redis
	Pool = &redigo.Pool{
		DialContext: func(ctx context.Context) (redigo.Conn, error) {
			var options = make([]redigo.DialOption, 0)
			options = append(options, redigo.DialDatabase(redisConfig.Db))
			if redisConfig.Password != "" {
				options = append(options, redigo.DialPassword(redisConfig.Password))
			}
			if redisConfig.WriteTimeout !=  0 {
				options = append(options, redigo.DialWriteTimeout(redisConfig.WriteTimeout))
			}
			if redisConfig.ReadTimeout != 0 {
				options = append(options, redigo.DialReadTimeout(redisConfig.ReadTimeout))
			}
			if redisConfig.ConnectTimeout != 0 {
				options = append(options, redigo.DialConnectTimeout(redisConfig.ConnectTimeout))
			}
			return redigo.DialContext(ctx, "tcp", redisConfig.Url, options...)
		},
		TestOnBorrow: func(c redigo.Conn, t time.Time) error {
			if _, err := redigo.String(c.Do("PING")); err != nil {
				return err
			}
			return nil
		},
		MaxIdle:         redisConfig.Pool.MaxIdle,
		MaxActive:       redisConfig.Pool.MaxActive,
		IdleTimeout:     redisConfig.Pool.IdleTimeout,
		Wait:            redisConfig.Pool.Wait,
		MaxConnLifetime: redisConfig.Pool.MaxConnLifetime,
	}
}


func Shutdown() error {
	if Pool == nil {
		return errors.New("poolδ��ʼ��")
	}
	return Pool.Close()
}
