const express = require('express')
const app = express();
const helmet = require('helmet')
const {Server} = require("socket.io");
const sdpTransform = require('sdp-transform');
const bodyParser = require('body-parser')

app.use(express.json()) // for json
app.use(express.urlencoded({ extended: true })) // for form data

app.use(helmet())
const cors=require("cors");
app.options('*', cors());
app.use(cors({
    credentials: true, // for authorization
}));
const port = 3100;
const port_socket = 3110;
const clients = new Set();

const io = new Server({ /* options */});


io.on("connection", (socket) => {
    console.log(socket.id);
    clients.add(socket.id);
    socket.on('onx', function(message)      {
        console.log(message);

    });


    socket.on('disconnect', function () {
        console.log(socket.id);
        clients.delete(socket.id);
    });

});

app.get('/', (req, res) => {
    try {
        res.status(200)
            .json({
                success: true,
                message: "Index Health Response"
            });
    } catch (error) {
        console.error(error)
        res.status(200).json({success: false, message: "Error: " + error.message})
    }

});


app.post('/parse-sdp/tojson', (req, res) => {
    try {
        const {sdp} = req.body;

        const resSdp = sdpTransform.parse(sdp);

        res.status(200)
            .json({
                success: true,
                message: "passed sdp",
                data: {
                    sdp: resSdp
                }
            });
    } catch (error) {
        console.error(error)
        res.status(200).json({success: false, message: "Error: " + error.message})
    }

});

app.post('/janus/events', (req, res) => {
    try {
        if( req.body && req.body.events){

        const {events} = req.body;
        console.log(req.body)

        io.emit('onNewEvent', JSON.stringify(events) );

        res.status(200)
            .json({
                success: true,
                message: "event-emitted",

            });

        }
        res.status(200)
            .json({
                success: false,
                message: "event-not-emitted",

            });

        console.log("none") ;
    } catch (error) {
        console.error(error)
        res.status(200).json({success: false, message: "Error: " + error.message})
    }

});
function sendTime() {
    io.emit('time', { time: new Date().toJSON() });
}

// Send current time every 10 secs
setInterval(sendTime, 10_000);

app.listen(port, () => {
    console.log(`Example app listening on port ${port}`)
})
io.listen(port_socket);