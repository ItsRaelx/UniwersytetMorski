using MongoDB.Bson;
using MongoDB.Driver;
using UMGProjektPP.Models;

namespace UMGProjektPP.Services
{
    public class EventService
    {
        private readonly IMongoCollection<Event> _events;

        public EventService(string connectionString, string databaseName, string collectionName)
        {
            var client = new MongoClient(connectionString);
            var database = client.GetDatabase(databaseName);
            _events = database.GetCollection<Event>(collectionName);
        }

        // Dodawanie eventu
        public async Task CreateEventAsync(Event eventObj)
        {
            await _events.InsertOneAsync(eventObj);
        }

        // Usuwanie eventu
        public async Task DeleteEventAsync(ObjectId id)
        {
            await _events.DeleteOneAsync(e => e.Id == id);
        }

        // Pobieranie wszystkich eventów
        public async Task<IEnumerable<Event>> GetAllEventsAsync()
        {
            return await _events.Find(e => true).ToListAsync();
        }
    }
}
