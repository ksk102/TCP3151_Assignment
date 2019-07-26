<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:report="http://www.w3.org/xml/report/"
  xmlns:found="http://www.w3.org/xml/found/"
  xmlns:claim="http://www.w3.org/xml/claim/">

  <xsl:template match="/">
    <html>
      <head>
        <title>Items to be Claimed</title>

        <style>
        .foundItem {
          font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
          border-collapse: collapse;
          width: 100%;
        }

        .foundItem td, .foundItem th {
          border: 1px solid #ddd;
          padding: 8px;
        }

        .foundItem tr:nth-child(even){background-color: #f2f2f2;}

        .foundItem tr:hover {background-color: #ddd;}

        .foundItem th {
          padding-top: 12px;
          padding-bottom: 12px;
          text-align: left;
          background-color: #c63f17;
          color: white;
        }
        </style>
      </head>
      
      <body>
        <h2>Items to be Claimed</h2>
        <xsl:apply-templates/>
      </body>
    </html>
  </xsl:template>

  <xsl:template match="item">
    <table class="foundItem">
      <tr>
        <th colspan="2"><xsl:apply-templates select="@id" /> - <xsl:apply-templates select="description" /></th>
      </tr>
      <tr><xsl:apply-templates select="found:datetime" /></tr>
      <tr><xsl:apply-templates select="location" /></tr>
      <tr><xsl:apply-templates select="detail" /></tr>
      <tr style="height:20px;"></tr>
    </table>
  </xsl:template>

  <xsl:template match="@id">
    <xsl:value-of select="."/>
  </xsl:template>

  <xsl:template match="description">
    <xsl:value-of select="."/>
  </xsl:template>

  <xsl:template match="found:datetime">
    <td style="width:130px">When its found: </td>
    <td><xsl:apply-templates select="found:date" /> @  <xsl:apply-templates select="found:time" /></td>
  </xsl:template>

  <xsl:template match="found:date">
    <xsl:value-of select="@year" />-<xsl:value-of select="@month" />-<xsl:value-of select="@date" />
  </xsl:template>

  <xsl:template match="found:time">
    <xsl:value-of select="@hour" />:<xsl:value-of select="@minute" />
  </xsl:template>

  <xsl:template match="location">
    <td>Location found: </td>
    <td><xsl:value-of select="."/></td>
  </xsl:template>

  <xsl:template match="detail">
    <td>Detail: </td>
    <td><xsl:value-of select="." /></td>
  </xsl:template>

</xsl:stylesheet>