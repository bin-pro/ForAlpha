const {createProxyMiddleware} = require('http-proxy-middleware')

module.exports = app => {
    app.use('/proxy',
        createProxyMiddleware(
            {
                target: 'http://test2.shinhan.site',
                changeOrigin: true,
            }
        )
    )
}