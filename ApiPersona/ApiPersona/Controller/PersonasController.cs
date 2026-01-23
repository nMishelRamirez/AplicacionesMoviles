using Microsoft.AspNetCore.Mvc;
using ApiPersonas.Models;
using ApiPersonas.Response;
using System.Collections.Generic;

namespace ApiPersonas.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class PersonasController : ControllerBase
    {
        private static List<Persona> personas = new List<Persona>();

        [HttpGet]
        public ActionResult Get()
        {
            return Ok(new ApiResponseList<Persona>
            {
                Estado = "Ok",
                Codigo = "200",
                Mensaje = "Listado de personas",
                Datos = personas
            });
        }

        [HttpPost]
        public ActionResult<Persona> Post([FromBody] Persona persona)
        {
            if (string.IsNullOrEmpty(persona.Nombre)){
                return BadRequest(new ApiResponse<Persona>
                {
                    Estado="Error",
                    Codigo="401",
                    Mensaje="El nombre es obligatorio...",
                    Datos=null
                }
                
                );

            }
            persona.Id= personas.Count+1;
            personas.Add(persona);
            return Ok(new ApiResponse<Persona>
                {
                    Estado="Ok",
                    Codigo="200",
                    Mensaje="Persona Registrada",
                    Datos=persona
                }

    );
            
        
        }


        [HttpPut("{id}")]
        public ActionResult Put(int i ,[FromBody] Persona persona)
        {
            var entidad=personas.FirstOrDefault(x => x.Id==persona.Id);
            if(entidad==null)  {  
             return BadRequest(new ApiResponse<Persona>
                {
                    Estado="Error",
                    Codigo="402",
                    Mensaje="La persona no existe...",
                    Datos=null
                }
             );
            }

                entidad.Nombre=persona.Nombre;
                entidad.Apellido=persona.Apellido;
                entidad.Cedula=persona.Cedula;
                return Ok(new ApiResponse<Persona>
                {
                    Estado="Ok",
                    Codigo="200",
                    Mensaje="Persona Actualizada",
                    Datos=entidad
                }
                );

        }

        
        [HttpDelete("{id}")]
        public ActionResult Delete(int id)
        {
            var entidad=personas.FirstOrDefault(x => x.Id==id);
            if (entidad==null) return NotFound();

            personas.Remove(entidad);
            return Ok("Persona eliminada");

        }
    }
}
