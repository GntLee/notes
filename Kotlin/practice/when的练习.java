
// ʹ��is���Զ�ת������,��� when �����

import java.lang.Exception

interface Expires

class Num(val number:Int):Expires
class Sum(val left:Num, val right:Num):Expires

fun eval(expires: Expires): Int {
    return  when (expires) {
        is Num -> expires.number
        is Sum -> eval(expires.left) + eval(expires.right)
        else -> throw Exception("");
    }
}

fun main(args:Array<String>){
    var value = eval(Sum(Num(1), Num(eval(Sum(Num(1),Num(5))))));
    println(value)
}


// �����쳣�ı��ʽ
fun foo(expires: Expires) = when (expires) {
        is Num -> expires.number
		// foo(expires.left) �쳣
		/*
			Ϊɶ? ����Ϊ�ݹ��ʱ��, �޷�ȷ���ϲ�ջ���ص���������ô???
		*/
        is Sum -> foo(expires.left) +  foo(expires.right)
        else -> throw Exception("")
    }
}

