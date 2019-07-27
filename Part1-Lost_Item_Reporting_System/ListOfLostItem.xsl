<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:report="http://www.w3.org/xml/report/"
  xmlns:lost="http://www.w3.org/xml/lost/"
  xmlns:claim="http://www.w3.org/xml/claim/">

<xsl:template match="/">
	<html>
    <head>
      <title>List of Lost Item</title>

      <style>
      #lostItem {
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        border-collapse: collapse;
        width: 100%;
      }

      #lostItem td, #lostItem th {
        border: 1px solid #ddd;
        padding: 8px;
      }

      #lostItem tr:nth-child(even){background-color: #f2f2f2;}

      #lostItem tr:hover {background-color: #ddd;}

      #lostItem th {
        padding-top: 12px;
        padding-bottom: 12px;
        text-align: left;
        background-color: #64b5f6;
        color: white;
      }

      .open {
        color: #66bb6a;
      }

      .closed {
        color: #ef5350;
      }

      .found {
        color: #ffa726;
      }
      </style>
    </head>
		<body>
			<h2>List of Lost Item</h2>
      <table id="lostItem">
        <tr>
          <th>Item ID</th>
          <th>Item</th>
          <th>Status</th>
          <th colspan="2">Lost between</th>
          <th>Where its lost</th>
          <th>Detail</th>
          <th>Report By</th>
          <th>Email</th>
          <th>Phone Number</th>
          <th>ID</th>
        </tr>

        <xsl:for-each select="lostReport/item">
          <!--<xsl:if test="status/@choice='Open'">-->
          <tr>
            <td><xsl:value-of select="@id" /></td>
            <td><xsl:value-of select="description" /></td>

            <xsl:choose>
              <xsl:when test="status/@choice='Open'">
                <td class="open"><xsl:value-of select="status/@choice" /></td>
              </xsl:when>
              <xsl:when test="status/@choice='Found'">
                <td class="found"><xsl:value-of select="status/@choice" /></td>
              </xsl:when>
              <xsl:otherwise>
                <td class="closed"><xsl:value-of select="status/@choice" /></td>
              </xsl:otherwise>
            </xsl:choose>
            <td>
              <xsl:value-of select="lost:datetime/lost:dateFrom/@year" />-<xsl:value-of select="lost:datetime/lost:dateFrom/@month" />-<xsl:value-of select="lost:datetime/lost:dateFrom/@date" />@<xsl:value-of select="lost:datetime/lost:timeFrom/@hour" />:<xsl:value-of select="lost:datetime/lost:timeFrom/@minute" /> 
            </td>
            <td>
              <xsl:value-of select="lost:datetime/lost:dateTo/@year" />-<xsl:value-of select="lost:datetime/lost:dateTo/@month" />-<xsl:value-of select="lost:datetime/lost:dateTo/@date" />@<xsl:value-of select="lost:datetime/lost:timeTo/@hour" />:<xsl:value-of select="lost:datetime/lost:timeTo/@minute" /> 
            </td>
            <td>
              <xsl:for-each select="locations/location">
                -<xsl:value-of select="." /><br/>
              </xsl:for-each>
            </td>
            <td><xsl:value-of select="detail" /></td>
            <td><xsl:value-of select="reportBy/name/firstName" /></td>
            <td><xsl:value-of select="reportBy/email" /></td>
            <td><xsl:value-of select="reportBy/phone" /></td>
            <td><xsl:value-of select="reportBy/id" /></td>
          </tr>
          <!--</xsl:if>-->
        </xsl:for-each>
      </table>
		</body>
	</html>
</xsl:template>
</xsl:stylesheet>