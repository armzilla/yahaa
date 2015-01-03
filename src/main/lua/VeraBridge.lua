

local http = require("socket.http")
local ltn12 = require("ltn12")
local apiUrl = 'http://192.168.1.22:8080/api/event'

function varcallback(deviceId, serviceName, variableName, previousValue, newValue)
    local reqbody = '{ \n\t"deviceId": ' .. deviceId .. ',\n\t"serviceName":"' .. serviceName .. '",\n\t"eventType": "' .. variableName .. '",\n\t"previousValue":' .. previousValue .. ',\n\t"value":' .. newValue .. '\n}'
    local respbody = {}

    local result, respcode, respheaders, respstatus = http.request {
        method = "POST",
        url = apiUrl,
        source = ltn12.source.string(reqbody),
        headers = {
            ["content-type"] = "application/json",
            ["content-length"] = tostring(#reqbody)
        },
        sink = ltn12.sink.null(respbody)
    }
end

luup.variable_watch("varcallback", "urn:upnp-org:serviceId:SwitchPower1")
luup.variable_watch("varcallback", "urn:micasaverde-com:serviceId:SecuritySensor1")
