using UMGProjektPP.Models;
using UMGProjektPP.Services;

namespace UMGProjektPP
{
    public partial class TokenForm : Form
    {
        private readonly User _user;
        private readonly UserService _userService;
        private readonly EventService _eventService;
        PictureBox pictureBox;

        public TokenForm(User user, UserService userService, EventService eventService)
        {
            InitializeComponent();
            _user = user;
            _userService = userService;
            _eventService = eventService;
            SetBackgroundImage();
            SetPanelImage();
            this.Resize += TokenForm_Resize;
        }

        private void btnNext_Click(object sender, EventArgs e)
        {
            var enteredToken = txtToken.Text;

            // Sprawdź czy token jest prawiedłowy
            if (_user.Token == enteredToken)
            {
                // Utwórz MainForm
                var mainForm = new MainForm(_user, _eventService);
                mainForm.Show();
                this.Close();
            }
            else
            {
                lblError.Text = "Błędny Token!";
            }
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
        private void TokenForm_Resize(object sender, EventArgs e)
        {
            // Ustawiamy nowe położenie
            txtToken.Location = new Point((this.ClientSize.Width - txtToken.Width) / 2, (this.ClientSize.Height - txtToken.Height - 9) / 2);

            btnNext.Location = new Point((this.ClientSize.Width - btnNext.Width + 258) / 2, (this.ClientSize.Height - btnNext.Height - 9) / 2);

            label1.Location = new Point((this.ClientSize.Width - label1.Width + 16) / 2, (this.ClientSize.Height - label1.Height - 143) / 2);

            pictureBox.Location = new Point((this.ClientSize.Width - pictureBox.Width) / 2, (this.ClientSize.Height - pictureBox.Height) / 2);
        }
    }
}
