import { PresentationDiagram } from "@/components/fleet/PresentationDiagram";

export const dynamic = "force-dynamic";

export default function DiagramaPage() {
  return (
    <main style={{ display: "grid", gap: 18, width: "min(1100px, 92vw)", margin: "0 auto", padding: "24px 0 40px" }}>
      <PresentationDiagram />
    </main>
  );
}
