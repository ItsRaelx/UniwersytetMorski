namespace UMGProjektPP
{
    partial class TokenForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            btnNext = new Button();
            lblError = new Label();
            label1 = new Label();
            txtToken = new TextBox();
            SuspendLayout();
            // 
            // btnNext
            // 
            btnNext.BackColor = Color.FromArgb(41, 42, 46);
            btnNext.FlatAppearance.BorderColor = Color.FromArgb(63, 131, 230);
            btnNext.FlatAppearance.BorderSize = 0;
            btnNext.FlatStyle = FlatStyle.Flat;
            btnNext.Image = Properties.Resources.next;
            btnNext.Location = new Point(461, 147);
            btnNext.Margin = new Padding(3, 2, 3, 2);
            btnNext.Name = "btnNext";
            btnNext.Size = new Size(35, 35);
            btnNext.TabIndex = 1;
            btnNext.UseVisualStyleBackColor = false;
            btnNext.Click += btnNext_Click;
            // 
            // lblError
            // 
            lblError.AutoSize = true;
            lblError.Location = new Point(328, 14);
            lblError.Name = "lblError";
            lblError.Size = new Size(0, 15);
            lblError.TabIndex = 2;
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.BackColor = Color.FromArgb(41, 42, 46);
            label1.Font = new Font("Lucida Sans Unicode", 36F, FontStyle.Bold, GraphicsUnit.Point);
            label1.ForeColor = SystemColors.Control;
            label1.Location = new Point(271, 68);
            label1.Name = "label1";
            label1.Size = new Size(174, 59);
            label1.TabIndex = 3;
            label1.Text = "Token";
            // 
            // txtToken
            // 
            txtToken.BackColor = Color.FromArgb(29, 29, 32);
            txtToken.BorderStyle = BorderStyle.None;
            txtToken.Font = new Font("Lucida Sans", 11.25F, FontStyle.Regular, GraphicsUnit.Point);
            txtToken.ForeColor = Color.FromArgb(241, 241, 241);
            txtToken.Location = new Point(255, 155);
            txtToken.Margin = new Padding(3, 2, 3, 2);
            txtToken.Name = "txtToken";
            txtToken.PlaceholderText = "Token";
            txtToken.Size = new Size(190, 18);
            txtToken.TabIndex = 4;
            txtToken.TextAlign = HorizontalAlignment.Center;
            // 
            // TokenForm
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(700, 338);
            Controls.Add(txtToken);
            Controls.Add(label1);
            Controls.Add(lblError);
            Controls.Add(btnNext);
            Margin = new Padding(3, 2, 3, 2);
            Name = "TokenForm";
            Text = "TokenForm";
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion
        private Button btnNext;
        private Label lblError;
        private Label label1;
        private TextBox txtToken;
    }
}