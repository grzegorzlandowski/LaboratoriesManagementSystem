const nav = new DayPilot.Navigator("nav");
nav.showMonths = 3;
nav.skipMonths = 3;
nav.selectMode = "week";
nav.onTimeRangeSelected = (args) => {
    dp.startDate = args.day;
    dp.update();
    dp.events.load("/api/events");
};
nav.init();


const dp = new DayPilot.Calendar("dp", {
    viewType: "Week",
    startDate: "2022-03-21",
    headerDateFormat: "dddd",
    onEventClick: async args => {

        const colors = [
            {name: "Blue", id: "#3c78d8"},
            {name: "Green", id: "#6aa84f"},
            {name: "Yellow", id: "#f1c232"},
            {name: "Red", id: "#cc0000"},
        ];

        const form = [
            {name: "Name", id: "text"},
            {name: "Color", id: "barColor", options: colors},
        ];

        const modal = await DayPilot.Modal.form(form, args.e.data);

        if (modal.canceled) {
            return;
        }

        dp.events.update(modal.result);

    },
    onBeforeEventRender: args => {
        args.data.barBackColor = "transparent";
        if (!args.data.barColor) {
            args.data.barColor = "#333";
        }
    },
    onTimeRangeSelected: async args => {

        const form = [
            {name: "Name", id: "text"}
        ];

        const data = {
            text: "Event"
        };

        const modal = await DayPilot.Modal.form(form, data);

        dp.clearSelection();

        if (modal.canceled) {
            return;
        }

        dp.events.add({
            start: args.start,
            end: args.end,
            id: DayPilot.guid(),
            text: modal.result.text,
            barColor: "#3c78d8"
        });
    }
});
dp.eventDeleteHandling = "Update";

dp.onEventDelete = function(args) {
    if (!swal("czy chcesz usunąć?", {
        buttons: {
            cancel: "Anuluj",
            Tak: "Tak",
        },
    })) {
        args.preventDefault();
    }
};

dp.onEventDeleted = function(args) {

    // AJAX call to the server, this example uses jQuery
    $.post("/event/delete/" + args.e.id(), function(result) {
        dp.message("Event deleted: " + args.e.text());
    });

};
dp.init();
