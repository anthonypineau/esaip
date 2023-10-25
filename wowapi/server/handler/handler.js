module.exports = {
    notFound(req, res, next) {
        const err = new Error('Not Found');
        err.status = 404;
        next(err);
    },
    
    errorHandler(err, req, res, next) {
        res.status(err.status || 500);
        res.json({
            message: err.message,
            //error: req.app.get('env') === 'development' ? err : {}
        });
    }
}