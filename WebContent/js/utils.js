//Fri Aug 30 2013 4:36 pm
console.log("time in util = " + formatAMPM(new Date()));

//using your function (passing in date)
function formatAMPM(date) {
    // gets the hours
    var hours = date.getHours();
    // gets the day
    var days = date.getDay();
    // gets the month
    var minutes = date.getMinutes();
    // gets AM/PM
    var ampm = hours >= 12 ? 'pm' : 'am';
    // converts hours to 12 hour instead of 24 hour
    hours = hours % 12;
    // converts 0 (midnight) to 12
    hours = hours ? hours : 12; // the hour '0' should be '12'
    // converts minutes to have leading 0
    minutes = minutes < 10 ? '0'+ minutes : minutes;
  
    // the time string
    var time = hours + ':' + minutes + ' ' + ampm;
  
    // gets the match for the date string we want
    var match = date.toString().match(/\w{3} \w{3} \d{1,2} \d{4}/);
  
    //the result
    return match[0] + ' ' + time;
}

function makeTable(container, data) {
    var table = $("<table/>").addClass('CSSTableGenerator');
    $.each(data, function(rowIndex, r) {
        var row = $("<tr/>");
        $.each(r, function(colIndex, c) { 
            row.append($("<t"+(rowIndex == 0 ?  "h" : "d")+"/>").text(c));
        });
        table.append(row);
    });
    return container.append(table);
}

function writeToFile(output){

	var fso = new ActiveXObject("Scripting.FileSystemObject");
	var fh = fso.OpenTextFile("issue.txt", 8, false, 0);

	fh.WriteLine(output);
	fh.Close();
}

