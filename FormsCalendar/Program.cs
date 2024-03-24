using UMGProjektPP.Services;

namespace UMGProjektPP
{
    internal static class Program
    {
        [STAThread]
        static void Main()
        {
            ApplicationConfiguration.Initialize();

            // Połącz się z bazą danych (User/Events)
            var userService = new UserService("", "CalendarApp", "Users");
            var eventService = new EventService("", "CalendarApp", "Events");

            // Przekaż obie klasy do LoginForma by rozpocząć logowanie
            Application.Run(new LoginForm(userService, eventService));
        }
    }
}
