using System.Net;
using System.Net.Mail;

namespace UMGProjektPP.Helpers
{
    public class EmailHelper
    {
        private readonly SmtpClient _smtpPoczta;

        public EmailHelper()
        {
            // Login i informacje o Sendinblue (Brevo) SMTP relay
            string smtpAdress = "smtp-relay.sendinblue.com";
            int smtpPort = 587;
            string smtpLogin = "";
            string smtpPass = "";

            // Utworzenie obiektu i zalogowanie
            _smtpPoczta = new SmtpClient(smtpAdress, smtpPort)
            {
                Credentials = new NetworkCredential(smtpLogin, smtpPass),
                // Sendinblue obsługuje SSL
                EnableSsl = true,
            };
        }

        public async Task SendEmailAsync(string email, string token)
        {
            using (var mail = new MailMessage())
            {
                // Z jakiego adressu ma "wysyłać" mail
                mail.From = new MailAddress("panel@raidhost.pl");

                // Do kogo
                mail.To.Add(email);

                // Temat
                mail.Subject = "Logowanie - Terminarz";

                // Token w emailu
                mail.Body = $"Twój token to: {token}";

                // Wyślij
                await _smtpPoczta.SendMailAsync(mail);
            }
        }

        // Generowanie tokenu (używane w LoginForm przy wysyłaniu maila)
        public string GenToken()
        {
            var random = new Random();
            // Znaki z jakich może się składać token
            const string znaki = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

            // Generowanie tokena (6 randomowych znaków z wcześniej)
            return new string(Enumerable.Repeat(znaki, 6)
                .Select(s => s[random.Next(s.Length)]).ToArray());
        }
    }
}
