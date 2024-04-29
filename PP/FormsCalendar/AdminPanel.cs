using MongoDB.Bson;
using UMGProjektPP.Models;
using UMGProjektPP.Services;

namespace UMGProjektPP
{
    public partial class AdminPanel : Form
    {
        private readonly User _administrator;
        private readonly EventService _eventService;

        public AdminPanel(User administrator, EventService eventService)
        {
            InitializeComponent();
            _administrator = administrator;
            _eventService = eventService;

            // Dodaj 24 h do wyboru w rozwijanym menu
            for (int i = 0; i < 24; i++)
            {
                string hour = i.ToString("D2") + ":00";
                cbFromHour.Items.Add(hour);
                cbToHour.Items.Add(hour);
            }

            LoadEventsAsync().ConfigureAwait(false);
        }

        // Załaduj wszystkie eventy (Przydatne przy odświeżaniu)
        private async Task LoadEventsAsync()
        {
            var events = await _eventService.GetAllEventsAsync();
            dataGridViewEvents.DataSource = new BindingSource { DataSource = events };
        }


        private async void btnDeleteEvent_Click_1(object sender, EventArgs e)
        {
            // Sprawdź czy cokolwiek zaznaczyłeś
            if (dataGridViewEvents.SelectedRows.Count == 0)
            {
                MessageBox.Show("Zaznacz pola do usunięcia!", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            DialogResult result = MessageBox.Show("Jesteś pewny że chcesz usunać ten event?", "Confirmation", MessageBoxButtons.YesNo, MessageBoxIcon.Warning);

            // Jesłi tak to usuń
            if (result == DialogResult.Yes)
            {
                // Pobierz id zaznaczonego eventu
                var eventId = ObjectId.Parse(dataGridViewEvents.SelectedRows[0].Cells["Id"].Value.ToString());

                // Użyj gotowej funkcji do usunięcia
                await _eventService.DeleteEventAsync(eventId);

                // zaktualizuj widok
                await LoadEventsAsync();
            }
        }

        private async void btnAddEvent_Click(object sender, EventArgs e)
        {
            string eventName = txtEventName.Text;
            string description = txtDescription.Text;
            DateTime eventDate = dtpEventDate.Value;
            string fromHour = cbFromHour.SelectedItem?.ToString();
            string toHour = cbToHour.SelectedItem?.ToString();

            // Sprawdź czy wszystkie pola nie są puste
            if (string.IsNullOrEmpty(eventName) || string.IsNullOrEmpty(description) || fromHour == null || toHour == null)
            {
                MessageBox.Show("Uzupełnij wszystkie pola!", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            // Utwórz obiekt eventu
            var newEvent = new Event
            {
                Name = eventName,
                Description = description,
                Date = eventDate,
                StartTime = TimeSpan.Parse(fromHour),
                EndTime = TimeSpan.Parse(toHour),
                AdministratorEmail = _administrator.Email
            };

            await _eventService.CreateEventAsync(newEvent);
            MessageBox.Show("Event zosatał dodany!", "Success", MessageBoxButtons.OK, MessageBoxIcon.Information);

            // Po dodaniu możesz wyczyścić pola tekstowe
            txtEventName.Clear();
            txtDescription.Clear();
            cbFromHour.SelectedItem = null;
            cbToHour.SelectedItem = null;

            // Uaktualnij widok po dodaniu
            await LoadEventsAsync();
        }
    }
}
