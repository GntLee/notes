package main

import (
	"fmt"
)

// ԭʼ��
type User struct {
	Name string
	Age int
	Hobby []string
}

// �������÷���
type UserConfig func(*User)

// ��������ʵ��
func Name(name string) UserConfig {
	return func(user *User) {
		user.Name = name
	}
}
func Age(age int) UserConfig {
	return func(user *User) {
		user.Age = age
	}
}
func Hobby(hobby []string) UserConfig {
	return func(user *User) {
		user.Hobby = hobby
	}
}

// ��������
func NewUser(configs ...UserConfig) *User {
	user := new(User)
	for _, config := range configs {
		config(user)
	}
	return user
}

func main() {
	// ͨ������������ʼ��
	user := NewUser(Hobby([]string {"Java", "Go"}), Name("����"))
	fmt.Println(user)
}