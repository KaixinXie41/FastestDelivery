import java.util.*

fun isValid(s: String): Boolean {

    var stack=Stack<String>()
    s.forEach {
        when(it.toString()){
            "{"->stack.push("}")

            "("->stack.push(")")
            "["->stack.push("]")
            else-> {
                if(stack.isEmpty()||stack.pop()!=it.toString()){
                    return false
                }
            }
        }
    }
    return stack.isEmpty()
}

fun main(args:Array<String>){

    var result= isValid("{[]}")
    println(result)
}