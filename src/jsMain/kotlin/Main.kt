import kotlinx.browser.document
import react.dom.render


@ExperimentalJsExport
fun main() {
    render(document.getElementById("root")) {
        child(App::class) {}
    }
}
