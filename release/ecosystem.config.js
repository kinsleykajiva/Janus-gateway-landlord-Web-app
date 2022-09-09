module.exports = {
  apps : [{
    name : 'node-app-server',
    script: 'node-app-server.js',
    watch: '.'
  }, {
    script: './service-worker/',
    watch: ['./service-worker'],
    ignore_watch:["nohup.out"]
  }],

};
