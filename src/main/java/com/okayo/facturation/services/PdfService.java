package com.okayo.facturation.services;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.okayo.facturation.entites.Facture;
import com.okayo.facturation.entites.LigneFacture;
import org.springframework.stereotype.Service;
import java.text.NumberFormat;
import java.io.ByteArrayOutputStream;
import java.util.Locale;

@Service
public class PdfService {

    public byte[] genererFacturePdf(Facture facture) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            // Création d'un formatter pour les montants
            NumberFormat formatter = NumberFormat.getInstance(Locale.FRANCE);
            formatter.setGroupingUsed(true); // espace pour les milliers
            formatter.setMaximumFractionDigits(0); // pas de décimales
            formatter.setMinimumFractionDigits(0);

            // En-tête principal
            document.add(new Paragraph("Facture n° " + facture.getReference(),
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
            document.add(new Paragraph("Date : " + facture.getDateFacture()));
            document.add(new Paragraph(" "));

            // Tableau pour émetteur (gauche) et client (droite)
            PdfPTable headerTable = new PdfPTable(2);
            headerTable.setWidthPercentage(100);
            headerTable.setSpacingAfter(10f);
            headerTable.setWidths(new float[]{1, 1}); // Deux colonnes égales

            // Émetteur à gauche
            PdfPCell emetteurCell = new PdfPCell();
            emetteurCell.setBorder(Rectangle.NO_BORDER);
            emetteurCell.addElement(new Paragraph("Émetteur :", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            emetteurCell.addElement(new Paragraph(facture.getEmetteur().getNom()));
            emetteurCell.addElement(new Paragraph(facture.getEmetteur().getBanque()));
            headerTable.addCell(emetteurCell);

            // Client à droite
            PdfPCell clientCell = new PdfPCell();
            clientCell.setBorder(Rectangle.NO_BORDER);
            clientCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            clientCell.addElement(new Paragraph("Adressé à :", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            clientCell.addElement(new Paragraph(facture.getClient().getNom()));
            clientCell.addElement(new Paragraph(facture.getClient().getAdresse()));
            headerTable.addCell(clientCell);

            document.add(headerTable);

            // Création d’un tableau à 5 colonnes
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100); // prend toute la largeur
            table.setSpacingBefore(10f);

            // Définir les largeurs relatives de chaque colonne
            table.setWidths(new float[]{3, 1, 2, 1.5f, 2});

            // En-têtes de colonnes
            table.addCell("Produit");
            table.addCell("Quantité");
            table.addCell("Prix HT");
            table.addCell("TVA");
            table.addCell("Total HT");

            // Remplissage des lignes de facture
            for (LigneFacture ligne : facture.getLigneFactures()) {
                table.addCell(ligne.getLibelleSnapshot());
                table.addCell(ligne.getQuantite().toString());
                table.addCell(formatter.format(ligne.getPuHtSnapshotCentimes()));
                table.addCell(String.format("%.1f %%", ligne.getTauxTvaSnapshot()));
                table.addCell(formatter.format(ligne.getTotalHtCentimes()));
            }

            // Ajouter le tableau au document
            document.add(table);

            // Total HT et TTC
            Paragraph totalHT = new Paragraph(
                    "Total HT : " + formatter.format(facture.getTotalHtCentimes()),
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)
            );
            totalHT.setAlignment(Element.ALIGN_RIGHT);
            document.add(totalHT);

            Paragraph totalTTC = new Paragraph(
                    "Total TTC : " + formatter.format(facture.getTotalTtcCentimes()),
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)
            );
            totalTTC.setAlignment(Element.ALIGN_RIGHT);
            document.add(totalTTC);
            document.close();
            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la génération du PDF : " + e.getMessage(), e);
        }
    }
}
