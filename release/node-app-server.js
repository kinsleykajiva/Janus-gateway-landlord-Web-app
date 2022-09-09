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
const messagesSets = new Set();

global.io = new Server({ /* options */});
io.listen(port_socket);

io.on("connection", (socket) => {
    console.log(socket.id + " connected");
    clients.add(socket);
    socket.on('onx', function(message)      {
        console.log(message);

    });
    io.emit('onNewJEventUser', socket.id + " connected" );

    socket.on('disconnect', function () {
        console.log(socket.id+ " disconnected");
        clients.delete(socket);
    });

});

app.get('/', async (req, res) => {
    try {
        return res.status(200)
            .json({
                success: true,
                message: "Index Health Response"
            });
    } catch (error) {
        console.error(error)
        return res.status(200).json({success: false, message: "Error: " + error.message})
    }

});


app.post('/parse-sdp/tojson', async(req, res) => {
    try {
        const {sdp} = req.body;

        const resSdp = sdpTransform.parse(sdp);

        return res.status(200)
            .json({
                success: true,
                message: "passed sdp",
                data: {
                    sdp: resSdp
                }
            });
    } catch (error) {
        console.error(error)
        return res.status(200).json({success: false, message: "Error: " + error.message})
    }

});

app.post('/janus/events', async (req, res) => {
    try {
        if( req.body && req.body.events){

        let {events} = req.body;
            console.log( events)
            events = JSON.parse(events)
        events.timestamp_posted=Date.now();
            messagesSets.add(events);
        io.emit('onNewJEvent', (events) );


            return res.status(200)
            .json({
                success: true,
                message: "event-emitted",

            });

        }
        console.log("none") ;
        return res.status(200)
            .json({
                success: false,
                message: "event-not-emitted",

            });


    } catch (error) {
        console.error(error)
        return res.status(200).json({success: false, message: "Error: " + error.message})
    }

});
function sendTime() {
    io.emit('time', JSON.stringify({ time: new Date() }));

}

// Send current time every 10 secs
setInterval(sendTime, 10_000);
setInterval( ()=> messagesSets.forEach( msg=>{  io.emit('onNewJEventUser', msg ); messagesSets.delete(msg); }),2_000);

app.listen(port, () => {
    console.log(` app listening on port ${port}`)
})
