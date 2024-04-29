namespace UMGProjektPP
{
    partial class MainForm
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
			this.flpEvents = new System.Windows.Forms.FlowLayoutPanel();
			this.btnRefresh1 = new UMGProjektPP.button();
			this.panel1 = new System.Windows.Forms.Panel();
			this.btnAdminPanel1 = new UMGProjektPP.button();
			this.panel1.SuspendLayout();
			this.SuspendLayout();
			// 
			// flpEvents
			// 
			this.flpEvents.AutoScroll = true;
			this.flpEvents.ForeColor = System.Drawing.SystemColors.ControlLight;
			this.flpEvents.Location = new System.Drawing.Point(10, 11);
			this.flpEvents.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
			this.flpEvents.Name = "flpEvents";
			this.flpEvents.Size = new System.Drawing.Size(592, 362);
			this.flpEvents.TabIndex = 4;
			// 
			// btnRefresh1
			// 
			this.btnRefresh1.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(41)))), ((int)(((byte)(42)))), ((int)(((byte)(46)))));
			this.btnRefresh1.BackgroundColor = System.Drawing.Color.FromArgb(((int)(((byte)(41)))), ((int)(((byte)(42)))), ((int)(((byte)(46)))));
			this.btnRefresh1.BorderColor = System.Drawing.Color.PaleVioletRed;
			this.btnRefresh1.BorderRadius = 20;
			this.btnRefresh1.BorderSize = 0;
			this.btnRefresh1.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
			this.btnRefresh1.Font = new System.Drawing.Font("Century Gothic", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
			this.btnRefresh1.ForeColor = System.Drawing.SystemColors.ControlLightLight;
			this.btnRefresh1.Location = new System.Drawing.Point(3, 64);
			this.btnRefresh1.Name = "btnRefresh1";
			this.btnRefresh1.Size = new System.Drawing.Size(94, 32);
			this.btnRefresh1.TabIndex = 4;
			this.btnRefresh1.Text = "Refresh";
			this.btnRefresh1.TextColor = System.Drawing.SystemColors.ControlLightLight;
			this.btnRefresh1.UseVisualStyleBackColor = false;
			this.btnRefresh1.Click += new System.EventHandler(this.btnRefresh1_Click);
			// 
			// panel1
			// 
			this.panel1.BackColor = System.Drawing.Color.White;
			this.panel1.Controls.Add(this.btnAdminPanel1);
			this.panel1.Controls.Add(this.btnRefresh1);
			this.panel1.Location = new System.Drawing.Point(600, -38);
			this.panel1.Name = "panel1";
			this.panel1.Size = new System.Drawing.Size(136, 476);
			this.panel1.TabIndex = 5;
			// 
			// btnAdminPanel1
			// 
			this.btnAdminPanel1.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(41)))), ((int)(((byte)(42)))), ((int)(((byte)(46)))));
			this.btnAdminPanel1.BackgroundColor = System.Drawing.Color.FromArgb(((int)(((byte)(41)))), ((int)(((byte)(42)))), ((int)(((byte)(46)))));
			this.btnAdminPanel1.BorderColor = System.Drawing.Color.PaleVioletRed;
			this.btnAdminPanel1.BorderRadius = 20;
			this.btnAdminPanel1.BorderSize = 0;
			this.btnAdminPanel1.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
			this.btnAdminPanel1.Font = new System.Drawing.Font("Century Gothic", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
			this.btnAdminPanel1.ForeColor = System.Drawing.SystemColors.ControlLight;
			this.btnAdminPanel1.Location = new System.Drawing.Point(3, 351);
			this.btnAdminPanel1.Name = "btnAdminPanel1";
			this.btnAdminPanel1.Size = new System.Drawing.Size(94, 33);
			this.btnAdminPanel1.TabIndex = 6;
			this.btnAdminPanel1.Text = "Admin";
			this.btnAdminPanel1.TextColor = System.Drawing.SystemColors.ControlLight;
			this.btnAdminPanel1.UseVisualStyleBackColor = false;
			this.btnAdminPanel1.Click += new System.EventHandler(this.btnAdminPanel1_Click);
			// 
			// MainForm
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 17F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(41)))), ((int)(((byte)(42)))), ((int)(((byte)(46)))));
			this.ClientSize = new System.Drawing.Size(700, 383);
			this.Controls.Add(this.flpEvents);
			this.Controls.Add(this.panel1);
			this.Font = new System.Drawing.Font("Century Gothic", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
			this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedDialog;
			this.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
			this.MaximizeBox = false;
			this.MinimizeBox = false;
			this.Name = "MainForm";
			this.Text = "MainForm";
			this.panel1.ResumeLayout(false);
			this.ResumeLayout(false);

        }

        #endregion
        private FlowLayoutPanel flpEvents;
		private Panel panel1;
		private button btnRefresh1;
		private button btnAdminPanel1;
	}
}