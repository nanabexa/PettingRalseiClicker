
using System;
using System.Collections.Generic;
using System.Linq;

namespace EvaluadorExpresionesAritmeticas
{
static class EvaluadorExpresiones
{
    static private Stack<string> pila = new Stack<string>();
    static private List<string> operadores = new List<string> {"*", "/", "+", "-"};

    static private int CalcularOperacion(int x, int y, string operador)
    {
        int resultado = 0;

        switch (operador)
        {
            case "+":
            {
                resultado = x + y;
                break;
            }
            case "-":
            {
                resultado = x - y;
                break;
            }
            case "*":
            {
                resultado = x * y;
                break;
            }
            case "/":
            {
                resultado = x / y;
                break;
            }
            default:
                break;
        }

        return resultado;
    }

    static private List<string> StringToList(string expresion)
    {
        var listaExpresion = new List<string>();
        int indiceListaExpresion = 0;

        listaExpresion.Add(expresion[0].ToString());

        for (int i = 1; i < expresion.Length; i++)
        {
            var valor = expresion[i];

            if (char.IsDigit(valor) && operadores.Concat(new string[2] { "(", ")" }).Contains(listaExpresion.ElementAt(indiceListaExpresion)))
            {
                listaExpresion.Add(valor.ToString());
                indiceListaExpresion++;
            }
                else if (operadores.Concat(new string[2] { "(", ")" }).Contains(valor.ToString()))
            {
                listaExpresion.Add(valor.ToString());
                indiceListaExpresion++;
            }
                else
            {
                var numero = listaExpresion.ElementAt(indiceListaExpresion) + valor;

                listaExpresion.RemoveAt(indiceListaExpresion);
                listaExpresion.Add(numero);
            }
        }

        return listaExpresion;
    }

    static private int PrecedenciaOperador(string operador)
    {
        int precedencia = 0;

        if (operador == "*" || operador == "/")
        {
            precedencia = 1;
        }
        else if (operador == "+" || operador == "-")
        {
            precedencia = 2;
        }

        return precedencia;
    }

    static public string InFijaToPostFija(string expresionInfija)
    {
        string expresionPostFija = "";
        int numero = 0;

        expresionInfija = expresionInfija.Replace(" ", "");

        var listaExpresionInfija = StringToList(expresionInfija);

        pila.Push("(");
        listaExpresionInfija.Add(")");

        foreach (var x in listaExpresionInfija)
        {
            if (Int32.TryParse(x, out numero))
            {
                expresionPostFija += x + " ";
            }
            else if (x == "(")
            {
                pila.Push(x.ToString());
            }
            else if (operadores.Contains(x.ToString()))
            {
                var operadorPila = pila.Peek();

                int precedenciaOperadorPila = PrecedenciaOperador(operadorPila);
                int precedenciaOperadorExpresion = PrecedenciaOperador(x.ToString());

                while (precedenciaOperadorPila <= precedenciaOperadorExpresion && operadores.Contains(operadorPila))
                {
                    expresionPostFija += pila.Pop() + " ";
                    operadorPila = pila.Peek();
                    precedenciaOperadorPila = PrecedenciaOperador(operadorPila);
                }

                pila.Push(x.ToString());
            }
            else if (x == ")")
            {
                while (pila.Peek() != "(")
                {
                    expresionPostFija += pila.Pop() + " ";
                }

                pila.Pop();
            }
        }

        return expresionPostFija;
    }

    static public int EvaluarExpresionPostFija(string expresionInfija, out string expresionPostfija)
    {
        expresionPostfija = InFijaToPostFija(expresionInfija);

        int op1, op2 = 0;
        var listaPostFija = expresionPostfija.Split(' ').Where(s => !string.IsNullOrWhiteSpace(s)).ToList();
        int numero = 0;

        foreach (var x in listaPostFija)
        {
            if (Int32.TryParse(x, out numero))
            {
                pila.Push(x);
            }
            else if (operadores.Contains(x))
            {
                op1 = Convert.ToInt32(pila.Pop());
                op2 = Convert.ToInt32(pila.Pop());
                pila.Push(CalcularOperacion(op2, op1, x).ToString());
            }
        }

        return Convert.ToInt32(pila.Pop());
    }
}
}