
namespace ApiPersonas.Response
{

    public class ApiResponseList<T>{
    public string Estado {get;set;}
    public string Codigo {get;set;}
    public string Mensaje {get;set;}
    public List<T> Datos {get;set;}


    }

}