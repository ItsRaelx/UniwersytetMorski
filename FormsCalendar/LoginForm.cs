using System.ComponentModel.DataAnnotations;
using UMGProjektPP.Services;
using UMGProjektPP.Helpers;
using UMGProjektPP.Models;

namespace UMGProjektPP
{
    public partial class LoginForm : Form
    {
        private readonly UserService _userService;
        private readonly EventService _eventService;
        private readonly EmailHelper _emailHelper;
        PictureBox pictureBox;

        public LoginForm(UserService userService, EventService eventService)
        {
            InitializeComponent();
            _userService = userService;
            _eventService = eventService;
            _emailHelper = new EmailHelper();
            SetBackgroundImage();
            SetPanelImage();
            this.Resize += LoginForm_Resize;
        }

        private async void btnNext_Click(object sender, EventArgs e)
        {
            // Pobieranie emaila z pole tekstowego
            var email = txtEmail.Text;
            var emailValidator = new EmailAddressAttribute();

            // Sprawdzanie emaila
            if (!emailValidator.IsValid(email))
            {
                lblError.Text = "Nieprawiedłowy adress email!";
                return;
            }

            // Pobieranie użytkownika po emailu z bazy danych
            var user = await _userService.GetUserByEmailAsync(email);

            // Jeśli użytkownik jest pusty to utwórz nowego z jakimiś defaultowymi polami
            if (user == null)
            {
                // IsAdmin jest bardzo ważny bez niego nie można wejść do panelu admina
                user = new User { Email = email, IsAdmin = false };
                await _userService.CreateUserAsync(user);
            }

            // Utwórz token za pomocą funkcji z EmailHelper.cs
            var token = _emailHelper.GenToken();

            // Zaktualizuj użytkownika w bazie danych (Dodaj mu token)
            user.Token = token;
            await _userService.UpdateUserAsync(user);

            // Wyślij token na email użytkownika
            await _emailHelper.SendEmailAsync(email, token);

            // Otwórz TokenForm i przekaż wszystkie dane
            var tokenForm = new TokenForm(user, _userService, _eventService);
            tokenForm.Show();
            this.Hide();
        }
        private void SetBackgroundImage()
        {
            string imagePath = @"Images\background.png"; // Ścieżka do tła
            Image background = Image.FromFile(imagePath);
            this.BackgroundImage = background;
            this.BackgroundImageLayout = ImageLayout.Stretch; // Wybierz rozłożenie tła
        }
        private void CenterPictureBox()
        {
            if (pictureBox != null && pictureBox.Image != null)
            {
                pictureBox.Location = new Point((this.ClientSize.Width - pictureBox.Width) / 2, (this.ClientSize.Height - pictureBox.Height) / 2);
            }
        }
        private void SetPanelImage()
        {
            pictureBox = new PictureBox();
            // Załadowuje obraz
            try
            {
                Bitmap bmp = new Bitmap("Images/panel.png");
                pictureBox.Image = bmp;
                pictureBox.BackColor = Color.Transparent;
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error loading the image: " + ex.Message);
                return;
            }

            // Ustawiono SizeMode na "Zoom" aby obraz dopasował się do rozmiaru PictureBox
            pictureBox.SizeMode = PictureBoxSizeMode.Zoom;

            // Dodaj PictureBox do forma
            this.Controls.Add(pictureBox);

            // Ustaw rozmiar PictureBox na rozmiar obrazu
            pictureBox.Size = pictureBox.Image.Size;

            // Wyśrodkuj PictureBox na ekranie
            CenterPictureBox();
        }
        private void LoginForm_Resize(object sender, EventArgs e)
        {
            // Ustawiamy nowe położenie
            txtEmail.Location = new Point((this.ClientSize.Width - txtEmail.Width) / 2, (this.ClientSize.Height - txtEmail.Height - 9) / 2);

            btnNext.Location = new Point((this.ClientSize.Width - btnNext.Width + 258) / 2, (this.ClientSize.Height - btnNext.Height - 9) / 2);

            label1.Location = new Point((this.ClientSize.Width - label1.Width + 8) / 2, (this.ClientSize.Height - label1.Height - 140) / 2);

            pictureBox.Location = new Point((this.ClientSize.Width - pictureBox.Width) / 2, (this.ClientSize.Height - pictureBox.Height) / 2);
        }
    }
}
