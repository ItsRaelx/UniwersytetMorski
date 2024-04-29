# Event Scheduler
Event Scheduler allows users to manage their schedule by creating, editing, and managing a task list. 

## General Requirements
* The application allows adding, deleting, and editing events.
* The user can view his events in the form of a calendar.
* The user can set reminders for individual events.
* The application should allow changing the status of events to completed, pending or postponed.

## Functionalities
Event Scheduler contains the following functions:

### Browsing the schedule
Users can browse the schedule by day, week, and month, providing a visual representation of the schedule for easy navigation.

### Adding events
Users can add events to their schedule by entering the date, time, and description of the event. They can also set a reminder for upcoming events.

```csharp
// Adding event 
public async Task CreateEventAsync(Event eventObj) {
    await _events.InsertOneAsync(eventObj);
}

// Event object
public class Event {
    public ObjectId Id { get; set; }
    public string Name { get; set; }
    public string Description { get; set; }
    public DateTime Date { get; set; }
    public TimeSpan StartTime { get; set; }
    public TimeSpan EndTime { get; set; }
    public string AdministratorEmail { get; set; }
}

// Create event object
var newEvent = new Event {
    Name = eventName,
    Description = description,
    Date = eventDate,
    StartTime = TimeSpan.Parse(fromHour),
    EndTime = TimeSpan.Parse(toHour),
    AdministratorEmail = _administrator.Email
}
```

### Deleting events
Users can delete events from the schedule when they are no longer needed.

```csharp
// Deleting event
public async Task DeleteEventAsync(ObjectId id) {
    await _events.DeleteOneAsync(e => e.Id == id);
}
```

### Authorization
The program requires logging in before granting access to the schedule. This ensures that only authorized users can manage the schedule. Authorization works based on a unique token, which is sent to the user's email address using the Sendinblue tool.

### System Design
The system is written in C# language using the .NET Framework.

## Tools and Libraries Used
- `MongoDB` - Database for storing users and events.
- `MongoDB.Driver` - Allows executing operations on the database in the C# application.
- `Visual Studio` - Integrated development environment.
- `Windows Forms` - Graphical interfaces.

## System Implementation
The project consists of several layers.

### User Interface
The calendar owner's graphical interface contains:

- Graphical representation of scheduled events along with descriptions.
- Selection of the desired range of event hours.
- Add event button.
- Delete event button.
- Refresh button, which reloads the events from scratch.

### Business Layer
The business layer contains the Event class, which is used to represent the event and store its attributes, such as name, description, start and end date and time.

```csharp
private async void btnCreateEvent_Click(object sender, EventArgs e) {
    string eventName = txtEventName.Text;
    string description = txtDescription.Text;
    DateTime eventDate = dtpEventDate.Value;
    string fromHour = cbFromHour.SelectedItem?.ToString();
    string toHour = cbToHour.SelectedItem?.ToString();
    
    if (string.IsNullOrEmpty(eventName) || string.IsNullOrEmpty(description) ||
        fromHour == null || toHour == null) {
        MessageBox.Show("Fill all fields!", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
        return;
    }

    // Create event function
    public async Task CreateEventAsync(Event eventObj) {
        await _events.InsertOneAsync(eventObj);
    }

    // Refresh the view after adding event
    await LoadEventsAsync();
}

private async void btnDeleteEvent_Click(object sender, EventArgs e) {
    if (dataGridViewEvents.SelectedRows.Count == 0) {
        MessageBox.Show("Select fields to delete!", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
        return;
    }
    
    DialogResult result = MessageBox.Show("Are you sure you want to delete this event?", "Confirmation", MessageBoxButtons.YesNo, MessageBoxIcon.Warning);
    
    if (result == DialogResult.Yes) {
        // Get id of the selected event
        var eventId = ObjectId.Parse(dataGridViewEvents.SelectedRows[0].Cells["Id"].Value.ToString());
        // Use ready function to delete
        await _eventService.DeleteEventAsync(eventId);
        // Refresh the view
        await LoadEventsAsync();
    }

    // Deleting event function
    public async Task DeleteEventAsync(ObjectId id) {
        await _events.DeleteOneAsync(e => e.Id == id);
    }
}
```

### Database Layer
The database layer is responsible for storing data related to users and events. The project uses a MongoDB database. The database stores "Users" and "Events" collections.

```csharp
public UserService(string connectionString, string databaseName, string collectionName) {
    var client = new MongoClient(connectionString);
    var database = client.GetDatabase(databaseName);
    _users = database.GetCollection<User>(collectionName);
}
```

## Discrepancies
Functionalities that have not been implemented:

- Setting reminders for individual events.
- Editing existing events.
- Connection with API.

**The project was completed and turned out on time on 15.05.2023.**
