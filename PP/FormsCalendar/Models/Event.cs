using MongoDB.Bson;

namespace UMGProjektPP.Models
{
    // Obiekt Eventu
    public class Event
    {
        public ObjectId Id { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public DateTime Date { get; set; }
        public TimeSpan StartTime { get; set; }
        public TimeSpan EndTime { get; set; }
        public string AdministratorEmail { get; set; }
    }
}
