using MongoDB.Bson;
using System.ComponentModel.DataAnnotations;

namespace UMGProjektPP.Models
{
    // Obiekt Usera (Bardzo często używany)
    public class User
    {
        public ObjectId Id { get; set; }
        public string Email { get; set; }
        public string Token { get; set; }
        public bool IsAdmin { get; set; }
    }

}
