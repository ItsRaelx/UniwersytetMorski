namespace UMGProjektPP
{
    partial class AdminPanel
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
            txtEventName = new TextBox();
            label1 = new Label();
            label2 = new Label();
            label3 = new Label();
            txtDescription = new TextBox();
            dtpEventDate = new DateTimePicker();
            cbFromHour = new ComboBox();
            cbToHour = new ComboBox();
            btnAddEvent = new Button();
            btnDeleteEvent = new Button();
            dataGridViewEvents = new DataGridView();
            backgroundWorker1 = new System.ComponentModel.BackgroundWorker();
            label4 = new Label();
            ((System.ComponentModel.ISupportInitialize)dataGridViewEvents).BeginInit();
            SuspendLayout();
            // 
            // txtEventName
            // 
            txtEventName.Location = new Point(117, 219);
            txtEventName.Name = "txtEventName";
            txtEventName.Size = new Size(308, 27);
            txtEventName.TabIndex = 0;
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new Point(11, 219);
            label1.Name = "label1";
            label1.Size = new Size(89, 20);
            label1.TabIndex = 1;
            label1.Text = "Event Name";
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new Point(11, 257);
            label2.Name = "label2";
            label2.Size = new Size(85, 20);
            label2.TabIndex = 2;
            label2.Text = "Description";
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Location = new Point(11, 291);
            label3.Name = "label3";
            label3.Size = new Size(45, 20);
            label3.TabIndex = 3;
            label3.Text = "Date ";
            // 
            // txtDescription
            // 
            txtDescription.Location = new Point(117, 257);
            txtDescription.Name = "txtDescription";
            txtDescription.Size = new Size(308, 27);
            txtDescription.TabIndex = 4;
            // 
            // dtpEventDate
            // 
            dtpEventDate.Location = new Point(117, 291);
            dtpEventDate.Name = "dtpEventDate";
            dtpEventDate.Size = new Size(308, 27);
            dtpEventDate.TabIndex = 5;
            // 
            // cbFromHour
            // 
            cbFromHour.FormattingEnabled = true;
            cbFromHour.Items.AddRange(new object[] { "00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00" });
            cbFromHour.Location = new Point(117, 323);
            cbFromHour.Name = "cbFromHour";
            cbFromHour.Size = new Size(151, 28);
            cbFromHour.TabIndex = 6;
            // 
            // cbToHour
            // 
            cbToHour.FormattingEnabled = true;
            cbToHour.Items.AddRange(new object[] { "00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00" });
            cbToHour.Location = new Point(273, 323);
            cbToHour.Name = "cbToHour";
            cbToHour.Size = new Size(151, 28);
            cbToHour.TabIndex = 7;
            // 
            // btnAddEvent
            // 
            btnAddEvent.Location = new Point(11, 363);
            btnAddEvent.Name = "btnAddEvent";
            btnAddEvent.Size = new Size(411, 29);
            btnAddEvent.TabIndex = 8;
            btnAddEvent.Text = "Create";
            btnAddEvent.UseVisualStyleBackColor = true;
            btnAddEvent.Click += btnAddEvent_Click;
            // 
            // btnDeleteEvent
            // 
            btnDeleteEvent.Location = new Point(694, 219);
            btnDeleteEvent.Name = "btnDeleteEvent";
            btnDeleteEvent.Size = new Size(94, 29);
            btnDeleteEvent.TabIndex = 9;
            btnDeleteEvent.Text = "Delete";
            btnDeleteEvent.UseVisualStyleBackColor = true;
            btnDeleteEvent.Click += btnDeleteEvent_Click_1;
            // 
            // dataGridViewEvents
            // 
            dataGridViewEvents.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dataGridViewEvents.Location = new Point(11, 12);
            dataGridViewEvents.Name = "dataGridViewEvents";
            dataGridViewEvents.RowHeadersWidth = 51;
            dataGridViewEvents.RowTemplate.Height = 29;
            dataGridViewEvents.Size = new Size(776, 188);
            dataGridViewEvents.TabIndex = 10;
            // 
            // label4
            // 
            label4.AutoSize = true;
            label4.Location = new Point(11, 323);
            label4.Name = "label4";
            label4.Size = new Size(48, 20);
            label4.TabIndex = 11;
            label4.Text = "Hours";
            // 
            // AdminPanel
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(800, 451);
            Controls.Add(label4);
            Controls.Add(dataGridViewEvents);
            Controls.Add(btnDeleteEvent);
            Controls.Add(btnAddEvent);
            Controls.Add(cbToHour);
            Controls.Add(cbFromHour);
            Controls.Add(dtpEventDate);
            Controls.Add(txtDescription);
            Controls.Add(label3);
            Controls.Add(label2);
            Controls.Add(label1);
            Controls.Add(txtEventName);
            FormBorderStyle = FormBorderStyle.FixedDialog;
            MaximizeBox = false;
            MinimizeBox = false;
            Name = "AdminPanel";
            Text = "AdminPanel";
            ((System.ComponentModel.ISupportInitialize)dataGridViewEvents).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private TextBox txtEventName;
        private Label label1;
        private Label label2;
        private Label label3;
        private TextBox txtDescription;
        private DateTimePicker dtpEventDate;
        private ComboBox cbFromHour;
        private ComboBox cbToHour;
        private Button btnAddEvent;
        private Button btnDeleteEvent;
        private DataGridView dataGridViewEvents;
        private System.ComponentModel.BackgroundWorker backgroundWorker1;
        private Label label4;
    }
}