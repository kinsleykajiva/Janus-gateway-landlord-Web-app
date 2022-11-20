module.exports = {
  apps : [{
    name : 'node-app-server',
    script: 'node-app-server.js',
    watch: '.'
  }, {
    script: './',
    watch: ['./'],
    ignore_watch:["nohup.out"]
  }],

};
