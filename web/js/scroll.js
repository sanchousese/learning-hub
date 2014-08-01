var wheel = document.getElementById('courseContainer');
function report(ammout) {
    wheel.innerHTML = 'wheel ammout: ' + ammout;
}
function callback(event) {
    var normalized;
    if (event.wheelDelta) {
        normalized = (event.wheelDelta % 120 - 0) == -0 ? event.wheelDelta / 120 : event.wheelDelta / 12;
    } else {
        var rawAmmount = event.deltaY ? event.deltaY : event.detail;
        normalized = -(rawAmmount % 3 ? rawAmmount * 10 : rawAmmount / 3);
    }
    report(normalized);
}
var event = 'onwheel' in document ? 'wheel' : 'onmousewheel' in document ? 'mousewheel' : 'DOMMouseScroll';
window.addEventListener(event, callback);