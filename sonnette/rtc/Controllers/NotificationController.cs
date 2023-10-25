using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.SignalR;
using sonnette.rtc.Hubs;
using sonnette.rtc.Models;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace sonnette.rtc.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class NotificationController : ControllerBase
    {
        private readonly IHubContext<ChatHub> _hubContext;

        public NotificationController(IHubContext<ChatHub> hubContext)
        {
            _hubContext = hubContext;
        }

        // POST api/<NotificationController>
        [HttpPost]
        public IActionResult Post([FromBody] Sonnette value)
        {
            //SonnetteDAO.Insert(value);

            string message = "La sonnette n°"+value.Id+" a été pressée le "+value.Date
                +" avec un appui de type "+value.TypeAppui;
            _hubContext.Clients.All.SendAsync("ReceiveMessage", message);

            return NoContent();
            //return BadRequest();
        }
    }
}
