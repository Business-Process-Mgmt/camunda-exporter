"
<html>
    <body>
        <table style="height:63px;background-color:#30577a">
            <tbody>
                <tr>
                    <td>
                        <h2 style="text-align:center;">
                            <strong>
                                <span style="color:#ffffff">{if(fn:string-length($errorVar/*:MailHeader/text())) then
                                                            $errorVar/*:MailHeader/text() else 'Oracle Service Bus
                                                            Application Fault Alerts'}</span></strong>
                        </h2>
                    </td>
                </tr>
            </tbody>
        </table>
        <p>Hi,</p>
        <div>
            <p>This is an auto generated email to notify you about the error described below</p>
            <p>Please do not reply to this mail</p>
        </div>
        <div>
            <h3>
                <span style="text-decoration:underline;">Error Details</span>
            </h3>
            <table style="height:216px;border-color:#000000;" border="1">
                <tbody>
                    <tr>
                        <td style="background-color:#30577a;text-align:left">
                            <span style="color:#ffffff"><strong>Application Type</strong></span>
                        </td>
                        <td style="text-align:left">{if(fn:string-length($errorVar/*:Application/*:Type/text()))
                                                                             then $errorVar/*:Application/*:Type/text() else
                                                                             'Not Available'}</td>
                    </tr>
                    <tr>
                        <td style="background-color:#30577a;text-align:left">
                            <span style="color:#ffffff"><strong>Error Code</strong></span>
                        </td>
                        <td style="text-align:left">{if(fn:string-length($errorVar/*:ErrorCode/text()))
                                                                             then $errorVar/*:ErrorCode/text() else
                                                                             'Not Available'}</td>
                    </tr>
                    <tr>
                        <td style="background-color:#30577a;text-align:left">
                            <span style="color:#ffffff"><strong>Error Message</strong></span>
                        </td>
                        <td style="text-align:left">{if(fn:string-length($errorVar/*:ErrorMessage/text()))
                                                                             then $errorVar/*:ErrorMessage/text() else
                                                                             'Not Available'}</td>
                    </tr>
                    <tr>
                        <td style="background-color:#30577a;text-align:left">
                            <span style="color:#ffffff"><strong>Location</strong></span>
                        </td>
                        <td style="text-align:left">{if(fn:string-length($errorVar/*:ErrorLocation/text()))
                                                                             then $errorVar/*:ErrorLocation/text() else
                                                                             'Not Available'}</td>
                    </tr>
                    <tr>
                        <td style="background-color:#30577a;text-align:left">
                            <span style="color:#ffffff"><strong>Severity</strong></span>
                        </td>
                        <td style="text-align:left">{if(fn:string-length($errorVar/*:ErrorSeverity/text()))
                                                                             then $errorVar/*:ErrorSeverity/text() else
                                                                             'Not Available'}</td>
                    </tr>
                    <tr>
                        <td style="background-color:#30577a;text-align:left">
                            <span style="color:#ffffff"><strong>Manual Intervention Required</strong></span>
                        </td>
                        <td style="text-align:left">{if(fn:string-length($errorVar/*:ManualIntervention/text()))
                                                                             then $errorVar/*:ManualIntervention/text() else
                                                                             'Not Available'}</td>
                    </tr>
                </tbody>
            </table>
        </div>
       <div>
            <p></p>
            <h3>
                <span style="text-decoration:underline;">Additional Information</span>
            </h3>
             { if(fn:count($errorVar/*:AdditionalInfoList/*:AdditionalInfo)>0) then
            <table style="height:26px;border-color:#000000;" border="1">
                <tbody>
                    { for $additionalInfo in $errorVar/*:AdditionalInfoList/*:AdditionalInfo return
                    if((fn:string-length($additionalInfo/*:AdditionalInfoName/text())>0) or
                    (fn:string-length($additionalInfo/*:AdditionalInfoValue/text())>0)) then
                    <tr>
                        <td style="background-color:#30577a;text-align:left">
                            <span style="color:#ffffff">
                                <strong>{$additionalInfo/*:AdditionalInfoName/text()}</strong></span>
                        </td>
                        <td style="text-align:left">{$additionalInfo/*:AdditionalInfoValue/text()}</td>
                    </tr>
                    else() }
                </tbody>
            </table>
            else 'Not Available'}
        </div>
        <div>
            <p>Thank you</p>
        </div>
    </body>
</html>"
