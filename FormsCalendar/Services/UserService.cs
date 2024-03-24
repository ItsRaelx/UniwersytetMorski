using MongoDB.Driver;
using UMGProjektPP.Models;

namespace UMGProjektPP.Services
{
    public class UserService
    {
        private readonly IMongoCollection<User> _users;

        public UserService(string connectionString, string databaseName, string collectionName)
        {
            var client = new MongoClient(connectionString);
            var database = client.GetDatabase(databaseName);
            _users = database.GetCollection<User>(collectionName);
        }

        // Pobieranie użytkownika w bazie
        public async Task<User> GetUserByEmailAsync(string email)
        {
            return await _users.Find(user => user.Email == email).FirstOrDefaultAsync();
        }

        // Tworzenie użytkownika w bazie
        public async Task CreateUserAsync(User user)
        {
            await _users.InsertOneAsync(user);
        }

        // Aktualizowanie użytkownika w bazie
        public async Task UpdateUserAsync(User user)
        {
            await _users.ReplaceOneAsync(u => u.Id == user.Id, user);
        }
    }
}
