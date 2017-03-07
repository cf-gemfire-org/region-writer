package regionwriter

import org.apache.commons.lang.StringUtils
import org.apache.geode.cache.client.ClientCacheFactory
import org.apache.geode.cache.client.ClientRegionFactory
import org.apache.geode.cache.client.ClientRegionShortcut
import java.net.URI
import java.util.*


fun main(args: Array<String>) {
    if (args.size != 4) {
        println("USAGE: <locator_url> <number_entries> <value_size> <region_name>")
        System.exit(1)
    }
    var locatorUrl = args[0]
    var numberEntries = args[1].toLong()
    var valueSize = args[2].toInt()
    var regionName = args[3]

    var uri = URI.create(locatorUrl)

    var userInfo = uri.userInfo.split(":".toRegex(), 0)
    ClientAuthInitialize.clientUsername = userInfo[0]
    ClientAuthInitialize.clientPassword = userInfo[1]

    var props = Properties();
    props.setProperty("security-client-auth-init", "regionwriter.ClientAuthInitialize.create")
    var ccf = ClientCacheFactory(props)


    ccf.addPoolLocator(uri.host, uri.port)

    var client = ccf.create()
    var factory: ClientRegionFactory<Any, Any> = client.createClientRegionFactory(ClientRegionShortcut.PROXY)
    var region = factory.create(regionName)

    var value = StringUtils.leftPad("value", valueSize, '*')
    for (i in 1..numberEntries) {
        region.put("xx" + i.toString(), value)
        Thread.sleep(10)
    }

    println("hlelo world")
}
