using UMGProjektPP.Models;
using UMGProjektPP.Services;

namespace UMGProjektPP
{
    public partial class MainForm : Form
    {
        private readonly User _loggedInUser;
        private readonly EventService _eventService;

        public MainForm(User loggedInUser, EventService eventService)
        {
            InitializeComponent();
            _loggedInUser = loggedInUser;
            _eventService = eventService;

            // Set the visibility of the btnAdminPanel based on the IsAdmin property of the user
            btnAdminPanel1.Visible = _loggedInUser.IsAdmin;

            // Add FormClosed event handler
            this.FormClosed += MainForm_FormClosed;

            LoadEventsAsync();
        }

        private async void LoadEventsAsync()
        {
            // Pobierasz wszystkie eventy z bazy danych
            var events = await _eventService.GetAllEventsAsync();
            flpEvents.Controls.Clear();

            // Przed wyświetleniem sortujesz itemy od najnowszych
            var sortedEvents = events.OrderByDescending(e => e.Date).ThenByDescending(e => e.StartTime);

            foreach (var eventObj in sortedEvents)
            {
                var eventPanel = new Panel
                {
                    Width = 200,
                    Height = 100,
                    BorderStyle = BorderStyle.FixedSingle,
                    Margin = new Padding(10)
                };

                var eventNameLabel = new Label
                {
                    Text = eventObj.Name,
                    Font = new Font("Arial", 12, FontStyle.Bold),
                    Location = new Point(5, 5),
                    AutoSize = true
                };

                var eventDateLabel = new Label
                {
                    Text = eventObj.Date.ToString("dd/MM/yyyy"),
                    Font = new Font("Arial", 10),
                    Location = new Point(5, 30),
                    AutoSize = true
                };

                var eventTimeLabel = new Label
                {
                    Text = $"{eventObj.StartTime} - {eventObj.EndTime}",
                    Font = new Font("Arial", 10),
                    Location = new Point(5, 50),
                    AutoSize = true
                };
				var eventDescriptionLabel = new Label
				{
					Text = eventObj.Description,
					Font = new Font("Arial", 10),
					Location = new Point(5, 70),
					AutoSize = true
				};


				eventPanel.Controls.Add(eventNameLabel);
                eventPanel.Controls.Add(eventDateLabel);
                eventPanel.Controls.Add(eventTimeLabel);
				eventPanel.Controls.Add(eventDescriptionLabel);

				flpEvents.Controls.Add(eventPanel);
            }
        }



        // Jeśli zamkniesz to okno to aplikacja się wyłącza
        private void MainForm_FormClosed(object sender, FormClosedEventArgs e)
        {
            Application.Exit();
        }

		private void btnRefresh1_Click(object sender, EventArgs e)
		{
			LoadEventsAsync();
		}

		private void btnAdminPanel1_Click(object sender, EventArgs e)
		{
			if (_loggedInUser.IsAdmin)
			{
				// Otwieranie okienka panelu Admina
				var adminPanel = new AdminPanel(_loggedInUser, _eventService);
				adminPanel.Show();
			}
		}
	}
}
